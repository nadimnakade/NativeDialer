package com.crm.v2.telephony.provider

import android.content.Context
import android.util.Log
import com.crm.v2.common.constants.ApiConstants
import com.crm.v2.data.network.dto.cloudconnect.CallLogRequest
import com.crm.v2.data.network.dto.cloudconnect.CallLogEntry
import com.crm.v2.data.network.dto.cloudconnect.Click2CallRequest
import com.crm.v2.telephony.CallState
import com.crm.v2.telephony.TelephonyConfig
import com.crm.v2.telephony.TelephonyEvent
import com.crm.v2.telephony.TelephonyManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CloudConnectProvider @Inject constructor(
    @ApplicationContext private val context: Context,
) : TelephonyManager {

    companion object {
        private const val TAG = "CloudConnectProvider"
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .build()

    private val json = Json { ignoreUnknownKeys = true; isLenient = true }

    private val _callState = MutableStateFlow<CallState>(CallState.Idle)
    override val callState: Flow<CallState> = _callState.asStateFlow()

    private val _events = MutableSharedFlow<TelephonyEvent>(extraBufferCapacity = 10)
    override val events: Flow<TelephonyEvent> = _events.asSharedFlow()

    private val _registrationState = MutableStateFlow(false)
    override val registrationState: Flow<Boolean> = _registrationState.asStateFlow()

    private var currentConfig: TelephonyConfig? = null
    private var currentUniqueToken: String = ""

    override suspend fun register(config: TelephonyConfig) {
        currentConfig = config
        _callState.value = CallState.Registered
        _registrationState.value = true
        Log.d(TAG, "Registered with extension: ${config.cloudExtensionNumber}")
    }

    override suspend fun unregister() {
        currentConfig = null
        currentUniqueToken = ""
        _callState.value = CallState.Idle
        _registrationState.value = false
    }

    override suspend fun call(number: String) {
        val config = currentConfig ?: return
        _callState.value = CallState.Calling

        try {
            val requestBody = Click2CallRequest(
                tokenId = config.cloudTokenId,
                phoneNumber = number,
                extensionNumber = config.cloudExtensionNumber,
                extensionPassword = config.cloudExtensionPassword,
            )

            val jsonBody = json.encodeToString(
                Click2CallRequest.serializer(),
                requestBody
            )

            val url = ApiConstants.CloudConnect.BASE_URL + ApiConstants.CloudConnect.CLICK_2_CALL
            Log.d(TAG, "Calling $number via CloudConnect: $url")

            val request = Request.Builder()
                .url(url)
                .post(jsonBody.toRequestBody("application/json".toMediaType()))
                .build()

            withContext(Dispatchers.IO) {
                client.newCall(request).execute().use { response ->
                    val bodyString = response.body?.string() ?: ""
                    Log.d(TAG, "CloudConnect response: ${response.code} - $bodyString")

                    if (response.isSuccessful) {
                        val jsonResponse = JSONObject(bodyString)
                        val status = jsonResponse.optString("status", "")
                        currentUniqueToken = jsonResponse.optString("unique_token", "")

                        if (status.equals("SUCCESS", ignoreCase = true)) {
                            _callState.value = CallState.Ringing
                            _events.tryEmit(TelephonyEvent.CallConnected(currentUniqueToken))
                            Log.d(TAG, "Call initiated successfully. unique_token=$currentUniqueToken")
                        } else {
                            _callState.value = CallState.Error
                            val message = jsonResponse.optString("message", "Unknown error")
                            _events.tryEmit(TelephonyEvent.Error("Cloud call failed: $message"))
                            Log.e(TAG, "Call failed: $status - $message")
                        }
                    } else {
                        _callState.value = CallState.Error
                        _events.tryEmit(TelephonyEvent.Error("Cloud API error: ${response.code}"))
                        Log.e(TAG, "API error: ${response.code}")
                    }
                }
            }
        } catch (e: Exception) {
            _callState.value = CallState.Error
            _events.tryEmit(TelephonyEvent.Error("Cloud call failed: ${e::class.simpleName}: ${e.message}"))
            Log.e(TAG, "Call exception", e)
        }
    }

    override suspend fun answer() {
        Log.d(TAG, "Answer - handled server-side by CloudConnect")
    }

    override suspend fun hangup() {
        val wasActive = _callState.value.isActive
        val duration = 0
        _callState.value = CallState.Idle
        if (wasActive && currentUniqueToken.isNotEmpty()) {
            _events.tryEmit(TelephonyEvent.CallDisconnected(currentUniqueToken, duration))
            Log.d(TAG, "Hangup. unique_token=$currentUniqueToken")
        }
        currentUniqueToken = ""
    }

    override suspend fun hold() {
        if (_callState.value is CallState.Connected) {
            _callState.value = CallState.OnHold
            Log.d(TAG, "Call on hold")
        }
    }

    override suspend fun resume() {
        if (_callState.value is CallState.OnHold) {
            _callState.value = CallState.Connected
            Log.d(TAG, "Call resumed")
        }
    }

    override suspend fun transfer(number: String) {
        Log.d(TAG, "Transfer to $number - not supported by CloudConnect basic API")
    }

    override suspend fun conference(number: String) {
        Log.d(TAG, "Conference - not supported by CloudConnect basic API")
    }

    override suspend fun mute(enabled: Boolean) {
        Log.d(TAG, "Mute: $enabled")
    }

    override suspend fun speaker(enabled: Boolean) {
        Log.d(TAG, "Speaker: $enabled")
    }

    override suspend fun bluetooth(enabled: Boolean) {
        Log.d(TAG, "Bluetooth: $enabled")
    }

    override fun getCurrentState(): CallState = _callState.value

    fun getCurrentUniqueToken(): String = currentUniqueToken

    suspend fun getCallLog(
        startDate: String,
        endDate: String,
        uniqueToken: String? = null,
    ): Result<List<CallLogEntry>> {
        val config = currentConfig ?: return Result.failure(Exception("Not registered"))
        return try {
            val requestBody = CallLogRequest(
                tokenId = config.cloudTokenId,
                uniqueToken = uniqueToken,
            )
            val jsonBody = json.encodeToString(CallLogRequest.serializer(), requestBody)

            val url = "${ApiConstants.CloudConnect.BASE_URL}api/info/$startDate/$endDate/TENANT/callLog"
            Log.d(TAG, "Fetching call log: $url")

            val request = Request.Builder()
                .url(url)
                .post(jsonBody.toRequestBody("application/json".toMediaType()))
                .build()

            withContext(Dispatchers.IO) {
                client.newCall(request).execute().use { response ->
                    val bodyString = response.body?.string() ?: ""
                    Log.d(TAG, "Call log response: ${response.code} - $bodyString")

                    if (response.isSuccessful) {
                        val logResponse = json.decodeFromString(
                            com.crm.v2.data.network.dto.cloudconnect.CallLogResponse.serializer(),
                            bodyString
                        )
                        Result.success(logResponse.data)
                    } else {
                        Result.failure(Exception("API error: ${response.code}"))
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "getCallLog failed", e)
            Result.failure(e)
        }
    }
}
