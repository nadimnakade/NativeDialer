package com.crm.v2.data.config

import android.util.Log
import com.crm.v2.common.constants.ApiConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExtensionConfigProvider @Inject constructor(
    private val cloudConnectConfigProvider: CloudConnectConfigProvider,
) {
    companion object {
        private const val TAG = "EXTENSION_CONFIG"
        private const val EXTENSION_URL = "https://1up.co.in/NEXUS_API/api/UpdateStatus/GetExtension"
        private const val UPDATE_DIALER_URL = "https://1up.co.in/NEXUS_API/api/UpdateStatus/UpdateNewDialerStatus"
    }

    private val client = OkHttpClient.Builder().build()

    private val _extensionStatus = MutableStateFlow(ExtensionStatus())
    val extensionStatus: StateFlow<ExtensionStatus> = _extensionStatus.asStateFlow()

    data class ExtensionStatus(
        val isActive: Boolean = true,
        val isLoaded: Boolean = false,
        val errorMessage: String? = null,
    )

    suspend fun fetchAndApplyExtension(empCode: Int): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val url = "$EXTENSION_URL?EmpCode=$empCode"
            Log.d(TAG, "Fetching extension from: $url")
            val request = Request.Builder().url(url).get().build()
            val response = client.newCall(request).execute()
            Log.d(TAG, "Response code: ${response.code}")

            if (!response.isSuccessful) {
                Log.e(TAG, "Failed to fetch extension: ${response.code}")
                _extensionStatus.value = ExtensionStatus(isActive = true, isLoaded = true, errorMessage = null)
                return@withContext Result.failure(Exception("Failed to fetch extension: ${response.code}"))
            }

            val body = response.body?.string() ?: return@withContext Result.failure(Exception("Empty response"))
            Log.d(TAG, "API response body: $body")
            val json = JSONObject(body)

            val status = json.optString("Status", "Active")
            val didNumber = json.optString("DidNumber", "")
            val extension = json.optString("Extension", "")

            Log.d(TAG, "Parsed: status=$status, didNumber=$didNumber, extension=$extension")

            if (status != "Active") {
                Log.w(TAG, "Extension INACTIVE for empCode=$empCode - blocking dialer access")
                _extensionStatus.value = ExtensionStatus(
                    isActive = false,
                    isLoaded = true,
                    errorMessage = "Your extension has been deactivated. Contact admin."
                )
                return@withContext Result.failure(Exception("Extension inactive"))
            }

            if (extension.isBlank() || didNumber.isBlank()) {
                Log.e(TAG, "Invalid extension data: ext=$extension, did=$didNumber")
                _extensionStatus.value = ExtensionStatus(
                    isActive = false,
                    isLoaded = true,
                    errorMessage = "Invalid extension configuration."
                )
                return@withContext Result.failure(Exception("Invalid extension data"))
            }

            val dbConfig = CloudConnectConfig(
                baseUrl = json.optString("BaseUrl", cloudConnectConfigProvider.config.value.baseUrl),
                click2Call = json.optString("Click2CallPath", cloudConnectConfigProvider.config.value.click2Call),
                tokenId = json.optString("TokenId", cloudConnectConfigProvider.config.value.tokenId),
                extensionNumber = extension,
                extensionPassword = json.optString("ExtensionPassword", cloudConnectConfigProvider.config.value.extensionPassword),
                sipServer = json.optString("SipServer", cloudConnectConfigProvider.config.value.sipServer),
                sipPort = json.optString("SipPort", cloudConnectConfigProvider.config.value.sipPort),
                sipUsername = didNumber,
                sipPassword = json.optString("SipPassword", cloudConnectConfigProvider.config.value.sipPassword),
                sipRealm = json.optString("SipRealm", cloudConnectConfigProvider.config.value.sipRealm),
            )

            ApiConstants.updateFromConfig(dbConfig)
            Log.d(TAG, "ApiConstants.Sip.USERNAME = ${ApiConstants.Sip.USERNAME}")
            Log.d(TAG, "ApiConstants.CloudConnect.EXTENSION_NUMBER = ${ApiConstants.CloudConnect.EXTENSION_NUMBER}")
            Log.d(TAG, "ApiConstants.CloudConnect.TOKEN_ID = ${ApiConstants.CloudConnect.TOKEN_ID}")
            _extensionStatus.value = ExtensionStatus(isActive = true, isLoaded = true, errorMessage = null)
            Log.d(TAG, "Extension applied: ext=$extension, did=$didNumber, server=${dbConfig.sipServer}:${dbConfig.sipPort}")
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e(TAG, "Error fetching extension: ${e.message}", e)
            _extensionStatus.value = ExtensionStatus(isActive = true, isLoaded = true, errorMessage = null)
            Result.failure(e)
        }
    }

    suspend fun markAsNewDialer(empCode: Int): Boolean = withContext(Dispatchers.IO) {
        try {
            val url = "$UPDATE_DIALER_URL?EmpCode=$empCode"
            val request = Request.Builder().url(url).get().build()
            val response = client.newCall(request).execute()
            val body = response.body?.string() ?: ""
            Log.d(TAG, "markAsNewDialer: code=${response.code}, body=$body")
            response.isSuccessful && body.contains("Ok")
        } catch (e: Exception) {
            Log.e(TAG, "markAsNewDialer failed: ${e.message}", e)
            false
        }
    }
}
