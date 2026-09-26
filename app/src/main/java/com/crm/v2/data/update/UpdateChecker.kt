package com.crm.v2.data.update

import android.content.Context
import android.content.Intent
import android.util.Log
import android.util.Xml
import androidx.core.content.FileProvider
import com.crm.v2.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.xmlpull.v1.XmlPullParser
import java.io.File
import java.io.StringReader
import javax.inject.Inject
import javax.inject.Singleton

data class UpdateInfo(
    val latestVersion: String,
    val downloadUrl: String,
    val appName: String = "",
)

@Singleton
class UpdateChecker @Inject constructor() {
    companion object {
        private const val VERSION_CHECK_URL = "https://1up.co.in/NEXUS_API/prodialer_new.xml"
        private const val TAG = "UPDATE"
    }

    private val client = OkHttpClient.Builder().build()

    suspend fun checkForUpdate(): UpdateInfo? = withContext(Dispatchers.IO) {
        try {
            val request = Request.Builder().url(VERSION_CHECK_URL).get().build()
            val response = client.newCall(request).execute()
            if (!response.isSuccessful) return@withContext null

            val body = response.body?.string() ?: return@withContext null

            var version = ""
            var url = ""
            var name = ""

            val parser = Xml.newPullParser()
            parser.setInput(StringReader(body))

            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    when (parser.name) {
                        "version" -> version = readText(parser)
                        "url" -> url = readText(parser)
                        "name" -> name = readText(parser)
                    }
                }
                eventType = parser.next()
            }

            if (version.isBlank() || url.isBlank()) return@withContext null

            val serverVersion = normalizeVersion(version)
            val appVersion = normalizeVersion(BuildConfig.VERSION_NAME)

            if (compareVersions(serverVersion, appVersion) > 0) {
                UpdateInfo(
                    latestVersion = version.trim(),
                    downloadUrl = url.trim(),
                    appName = name.trim(),
                )
            } else null
        } catch (e: Exception) {
            null
        }
    }

    private fun readText(parser: XmlPullParser): String {
        var result = ""
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.text
            parser.nextTag()
        }
        return result
    }

    private fun normalizeVersion(version: String): String {
        return version.trim().removeSuffix("-debug")
    }

    private fun compareVersions(v1: String, v2: String): Int {
        val parts1 = v1.split(".").map { it.toIntOrNull() ?: 0 }
        val parts2 = v2.split(".").map { it.toIntOrNull() ?: 0 }
        val maxLen = maxOf(parts1.size, parts2.size)

        for (i in 0 until maxLen) {
            val p1 = parts1.getOrElse(i) { 0 }
            val p2 = parts2.getOrElse(i) { 0 }
            if (p1 != p2) return p1 - p2
        }
        return 0
    }

    suspend fun downloadAndInstall(context: Context, updateInfo: UpdateInfo) = withContext(Dispatchers.IO) {
        try {
            val updatesDir = File(context.cacheDir, "updates").apply { mkdirs() }
            val apkFile = File(updatesDir, "dialer-v${updateInfo.latestVersion}.apk")

            Log.d(TAG, "Downloading APK from ${updateInfo.downloadUrl}")

            val request = Request.Builder().url(updateInfo.downloadUrl).get().build()
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    Log.e(TAG, "Download failed: ${response.code}")
                    return@withContext
                }

                response.body?.byteStream()?.use { input ->
                    apkFile.outputStream().use { output ->
                        input.copyTo(output)
                    }
                }
            }

            Log.d(TAG, "APK downloaded to ${apkFile.absolutePath}, launching installer")

            val apkUri = FileProvider.getUriForFile(
                context,
                "${BuildConfig.APPLICATION_ID}.fileprovider",
                apkFile
            )

            val installIntent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(apkUri, "application/vnd.android.package-archive")
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(installIntent)
        } catch (e: Exception) {
            Log.e(TAG, "Install failed: ${e.message}", e)
        }
    }
}
