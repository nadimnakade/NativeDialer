package com.crm.v2.data.config

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.util.Xml
import com.crm.v2.common.constants.ApiConstants
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.xmlpull.v1.XmlPullParser
import java.io.StringReader
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CloudConnectConfigProvider @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    companion object {
        private const val TAG = "CLOUD_CONNECT"
        private const val CONFIG_URL = "https://1up.co.in/DialerAPI/cloudconnect.xml"
        private const val PREFS_NAME = "cloud_connect_config"
        private const val KEY_SAVED = "config_saved"
    }

    private val client = OkHttpClient.Builder().build()
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val _config = MutableStateFlow(loadCachedConfig())
    val config: StateFlow<CloudConnectConfig> = _config.asStateFlow()

    init {
        ApiConstants.updateFromConfig(_config.value)
    }

    suspend fun fetchConfig(): CloudConnectConfig = withContext(Dispatchers.IO) {
        try {
            val request = Request.Builder().url(CONFIG_URL).get().build()
            val response = client.newCall(request).execute()
            if (!response.isSuccessful) {
                Log.e(TAG, "Failed to fetch config: ${response.code}")
                return@withContext _config.value
            }

            val body = response.body?.string() ?: return@withContext _config.value

            var baseUrl = ""
            var click2Call = ""
            var tokenId = ""
            var extensionNumber = ""
            var extensionPassword = ""
            var sipServer = ""
            var sipPort = ""
            var sipUsername = ""
            var sipPassword = ""
            var sipRealm = ""

            val parser = Xml.newPullParser()
            parser.setInput(StringReader(body))

            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    when (parser.name) {
                        "baseUrl" -> baseUrl = readText(parser)
                        "click2call" -> click2Call = readText(parser)
                        "tokenId" -> tokenId = readText(parser)
                        "extensionNumber" -> extensionNumber = readText(parser)
                        "extensionPassword" -> extensionPassword = readText(parser)
                        "sipServer" -> sipServer = readText(parser)
                        "sipPort" -> sipPort = readText(parser)
                        "sipUsername" -> sipUsername = readText(parser)
                        "sipPassword" -> sipPassword = readText(parser)
                        "sipRealm" -> sipRealm = readText(parser)
                    }
                }
                eventType = parser.next()
            }

            val newConfig = CloudConnectConfig(
                baseUrl = baseUrl.ifBlank { _config.value.baseUrl },
                click2Call = click2Call.ifBlank { _config.value.click2Call },
                tokenId = tokenId.ifBlank { _config.value.tokenId },
                extensionNumber = extensionNumber.ifBlank { _config.value.extensionNumber },
                extensionPassword = extensionPassword.ifBlank { _config.value.extensionPassword },
                sipServer = sipServer.ifBlank { _config.value.sipServer },
                sipPort = sipPort.ifBlank { _config.value.sipPort },
                sipUsername = sipUsername.ifBlank { _config.value.sipUsername },
                sipPassword = sipPassword.ifBlank { _config.value.sipPassword },
                sipRealm = sipRealm.ifBlank { _config.value.sipRealm }
            )

            _config.value = newConfig
            ApiConstants.updateFromConfig(newConfig)
            saveConfig(newConfig)
            Log.d(TAG, "Config loaded from server: ext=${newConfig.extensionNumber}, sipUser=${newConfig.sipUsername}")
            newConfig
        } catch (e: Exception) {
            Log.e(TAG, "Error fetching config: ${e.message}", e)
            _config.value
        }
    }

    private fun loadCachedConfig(): CloudConnectConfig {
        if (!prefs.getBoolean(KEY_SAVED, false)) {
            return CloudConnectConfig()
        }
        return CloudConnectConfig(
            baseUrl = prefs.getString("baseUrl", CloudConnectConfig().baseUrl)!!,
            click2Call = prefs.getString("click2Call", CloudConnectConfig().click2Call)!!,
            tokenId = prefs.getString("tokenId", CloudConnectConfig().tokenId)!!,
            extensionNumber = prefs.getString("extensionNumber", CloudConnectConfig().extensionNumber)!!,
            extensionPassword = prefs.getString("extensionPassword", CloudConnectConfig().extensionPassword)!!,
            sipServer = prefs.getString("sipServer", CloudConnectConfig().sipServer)!!,
            sipPort = prefs.getString("sipPort", CloudConnectConfig().sipPort)!!,
            sipUsername = prefs.getString("sipUsername", CloudConnectConfig().sipUsername)!!,
            sipPassword = prefs.getString("sipPassword", CloudConnectConfig().sipPassword)!!,
            sipRealm = prefs.getString("sipRealm", CloudConnectConfig().sipRealm)!!
        )
    }

    private fun saveConfig(config: CloudConnectConfig) {
        prefs.edit().apply {
            putBoolean(KEY_SAVED, true)
            putString("baseUrl", config.baseUrl)
            putString("click2Call", config.click2Call)
            putString("tokenId", config.tokenId)
            putString("extensionNumber", config.extensionNumber)
            putString("extensionPassword", config.extensionPassword)
            putString("sipServer", config.sipServer)
            putString("sipPort", config.sipPort)
            putString("sipUsername", config.sipUsername)
            putString("sipPassword", config.sipPassword)
            putString("sipRealm", config.sipRealm)
            apply()
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
}
