package com.crm.v2.presentation.ui.dialer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crm.v2.data.datastore.AuthDataStore
import com.crm.v2.domain.model.DialerRecord
import com.crm.v2.domain.repository.DialerRepository
import com.crm.v2.telephony.CallState
import com.crm.v2.telephony.TelephonyConfig
import com.crm.v2.telephony.TelephonyEvent
import com.crm.v2.telephony.TelephonyManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DialerUiState(
    val activeTab: Int = 0,
    val phoneNumber: String = "",
    val dialerQueue: List<DialerRecord> = emptyList(),
    val currentLead: DialerRecord? = null,
    val callState: CallState = CallState.Idle,
    val isAutoDialing: Boolean = false,
    val autoDialInterval: Int = 10,
    val dndStatus: String? = null,
    val error: String? = null,
    val isLoading: Boolean = false,
    val lastCallMessage: String? = null,
)

@HiltViewModel
class DialerViewModel @Inject constructor(
    private val dialerRepository: DialerRepository,
    private val telephonyManager: TelephonyManager,
    private val authDataStore: AuthDataStore,
) : ViewModel() {

    private val _uiState = MutableStateFlow(DialerUiState())
    val uiState: StateFlow<DialerUiState> = _uiState.asStateFlow()

    private var autoDialJob: Job? = null

    init {
        viewModelScope.launch {
            telephonyManager.callState.collect { state ->
                _uiState.update { it.copy(callState = state, isLoading = false) }
                if (state is CallState.Idle && _uiState.value.currentLead != null) {
                    if (_uiState.value.isAutoDialing) {
                        autoDialNext()
                    }
                }
            }
        }

        viewModelScope.launch {
            telephonyManager.events.collect { event ->
                when (event) {
                    is TelephonyEvent.Error -> {
                        _uiState.update { it.copy(error = event.message, isLoading = false) }
                    }
                    is TelephonyEvent.CallConnected -> {
                        _uiState.update { it.copy(lastCallMessage = "Call connected", isLoading = false) }
                    }
                    is TelephonyEvent.CallDisconnected -> {
                        _uiState.update { it.copy(lastCallMessage = "Call ended", isLoading = false) }
                    }
                    is TelephonyEvent.RegistrationFailed -> {
                        _uiState.update { it.copy(error = "Telephony registration failed", isLoading = false) }
                    }
                    else -> {}
                }
            }
        }
    }

    fun setActiveTab(tab: Int) {
        _uiState.update { it.copy(activeTab = tab) }
    }

    fun onPhoneNumberChanged(number: String) {
        _uiState.update { it.copy(phoneNumber = number, dndStatus = null, error = null) }
    }

    fun loadDialerQueue() {
        val userKey = authDataStore.userKey ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            dialerRepository.getDialerData(userKey).let { result ->
                when (result) {
                    is com.crm.v2.common.result.Resource.Success -> _uiState.update {
                        it.copy(dialerQueue = result.data, isLoading = false)
                    }
                    is com.crm.v2.common.result.Resource.Error -> _uiState.update {
                        it.copy(error = result.message, isLoading = false)
                    }
                    else -> {}
                }
            }
        }
    }

    fun startAutoDial() {
        val userKey = authDataStore.userKey ?: return
        _uiState.update { it.copy(isAutoDialing = true) }

        viewModelScope.launch {
            telephonyManager.register(
                TelephonyConfig(
                    providerType = com.crm.v2.telephony.ProviderType.PJSIP,
                    sipUsername = com.crm.v2.common.constants.ApiConstants.Sip.USERNAME,
                    sipPassword = com.crm.v2.common.constants.ApiConstants.Sip.PASSWORD,
                    sipServer = "${com.crm.v2.common.constants.ApiConstants.Sip.SERVER}:${com.crm.v2.common.constants.ApiConstants.Sip.PORT}",
                )
            )
            dialerRepository.startAutoDial(userKey).collect { queue ->
                _uiState.update { it.copy(dialerQueue = queue) }
                if (queue.isNotEmpty()) {
                    autoDialNext()
                }
            }
        }
    }

    fun stopAutoDial() {
        autoDialJob?.cancel()
        autoDialJob = null
        _uiState.update { it.copy(isAutoDialing = false, currentLead = null) }
        viewModelScope.launch {
            telephonyManager.hangup()
            telephonyManager.unregister()
        }
    }

    fun manualCall(number: String) {
        val trimmed = number.trim()
        if (trimmed.length != 10) {
            _uiState.update { it.copy(error = "Enter valid 10-digit number") }
            return
        }
        if (trimmed.any { !it.isDigit() }) {
            _uiState.update { it.copy(error = "Number must contain only digits") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null, lastCallMessage = null) }
            try {
                telephonyManager.register(
                    TelephonyConfig(
                        providerType = com.crm.v2.telephony.ProviderType.PJSIP,
                        sipUsername = com.crm.v2.common.constants.ApiConstants.Sip.USERNAME,
                        sipPassword = com.crm.v2.common.constants.ApiConstants.Sip.PASSWORD,
                        sipServer = "${com.crm.v2.common.constants.ApiConstants.Sip.SERVER}:${com.crm.v2.common.constants.ApiConstants.Sip.PORT}",
                    )
                )
                telephonyManager.call(trimmed)
            } catch (e: Exception) {
                _uiState.update { it.copy(error = "Call failed: ${e.message}", isLoading = false) }
            }
        }
    }

    fun hangup() {
        viewModelScope.launch {
            telephonyManager.hangup()
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }

    fun toggleMute() { viewModelScope.launch { telephonyManager.mute(true) } }
    fun toggleSpeaker() { viewModelScope.launch { telephonyManager.speaker(true) } }
    fun toggleHold() {
        viewModelScope.launch {
            if (_uiState.value.callState is CallState.OnHold) telephonyManager.resume()
            else telephonyManager.hangup()
        }
    }

    private fun autoDialNext() {
        autoDialJob?.cancel()
        autoDialJob = viewModelScope.launch {
            val queue = _uiState.value.dialerQueue
            val index = queue.indexOfFirst { it.key == _uiState.value.currentLead?.key }
            val nextIndex = index + 1

            if (nextIndex < queue.size) {
                delay(_uiState.value.autoDialInterval * 1000L)
                val lead = queue[nextIndex]
                _uiState.update { it.copy(currentLead = lead) }
                telephonyManager.call(lead.contact)
            } else {
                _uiState.update { it.copy(isAutoDialing = false) }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        autoDialJob?.cancel()
    }
}
