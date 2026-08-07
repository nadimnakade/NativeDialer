package com.crm.v2.presentation.ui.dialer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crm.v2.common.constants.ApiConstants
import com.crm.v2.data.datastore.AuthDataStore
import com.crm.v2.domain.model.Contact
import com.crm.v2.domain.model.DialerRecord
import com.crm.v2.domain.repository.ContactRepository
import com.crm.v2.domain.repository.DialerRepository
import com.crm.v2.domain.repository.LeadRepository
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
    val leadList: List<DialerRecord> = emptyList(),
    val currentLead: DialerRecord? = null,
    val callState: CallState = CallState.Idle,
    val isAutoDialing: Boolean = false,
    val autoDialInterval: Int = 5,
    val dndStatus: String? = null,
    val error: String? = null,
    val isLoading: Boolean = false,
    val isSaving: Boolean = false,
    val lastCallMessage: String? = null,
    val contactSearchQuery: String = "",
    val groupedContacts: Map<Char, List<Contact>> = emptyMap(),
    val allContacts: List<Contact> = emptyList(),
    val isRefreshing: Boolean = false,
    val leadPageIndex: Int = 0,
    val hasMoreLeads: Boolean = true,
    val showUpdateStatus: Boolean = false,
    val callDuration: Int = 0,
    val isMuted: Boolean = false,
    val isSpeakerOn: Boolean = false,
    val isOnHold: Boolean = false,
)

@HiltViewModel
class DialerViewModel @Inject constructor(
    private val dialerRepository: DialerRepository,
    private val leadRepository: LeadRepository,
    private val contactRepository: ContactRepository,
    private val telephonyManager: TelephonyManager,
    private val authDataStore: AuthDataStore,
) : ViewModel() {

    private val _uiState = MutableStateFlow(DialerUiState())
    val uiState: StateFlow<DialerUiState> = _uiState.asStateFlow()

    private var autoDialJob: Job? = null
    private var contactJob: Job? = null
    private var callStartTime: Long = 0L
    private var lastCalledIndex: Int = -1

    init {
        viewModelScope.launch {
            telephonyManager.callState.collect { state ->
                val wasActive = _uiState.value.callState.isActive
                val nowIdle = state is CallState.Idle
                _uiState.update { it.copy(callState = state) }

                if (wasActive && nowIdle && _uiState.value.currentLead != null) {
                    val lead = _uiState.value.currentLead!!
                    val duration = if (callStartTime > 0) ((System.currentTimeMillis() - callStartTime) / 1000).toInt() else 0
                    callStartTime = 0L
                    _uiState.update {
                        it.copy(
                            callDuration = duration,
                            showUpdateStatus = true,
                            isMuted = false,
                            isSpeakerOn = false,
                            isOnHold = false,
                            isLoading = false,
                        )
                    }
                    if (lead.key.isNotEmpty()) {
                        viewModelScope.launch {
                            dialerRepository.updateDialingStatus(lead.key)
                        }
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
                        callStartTime = System.currentTimeMillis()
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

        loadContacts()
    }

    fun setActiveTab(tab: Int) {
        _uiState.update { it.copy(activeTab = tab) }
        when (tab) {
            0 -> loadDialerQueue()
            1 -> loadLeads(refresh = false)
            2 -> loadContacts()
        }
    }

    fun setCurrentLead(lead: DialerRecord) {
        _uiState.update { it.copy(currentLead = lead) }
    }

    fun onPhoneNumberChanged(number: String) {
        _uiState.update { it.copy(phoneNumber = number, dndStatus = null, error = null) }
    }

    fun clearManualNumber() {
        _uiState.update { it.copy(phoneNumber = "", dndStatus = null) }
    }

    fun onContactSearchChanged(query: String) {
        _uiState.update { it.copy(contactSearchQuery = query) }
        if (query.isBlank()) {
            val grouped = _uiState.value.allContacts.groupBy { it.name?.firstOrNull()?.uppercaseChar() ?: '#' }
            _uiState.update { it.copy(groupedContacts = grouped) }
        } else {
            val filtered = _uiState.value.allContacts.filter { contact ->
                contact.name?.contains(query, ignoreCase = true) == true ||
                contact.phoneNumbers.any { it.contains(query) }
            }
            val grouped = filtered.groupBy { it.name?.firstOrNull()?.uppercaseChar() ?: '#' }
            _uiState.update { it.copy(groupedContacts = grouped) }
        }
    }

    private fun loadContacts() {
        contactJob?.cancel()
        contactJob = viewModelScope.launch {
            contactRepository.getCachedContacts().collect { contacts ->
                val grouped = contacts.groupBy { it.name?.firstOrNull()?.uppercaseChar() ?: '#' }
                _uiState.update { it.copy(allContacts = contacts, groupedContacts = grouped) }
            }
        }
    }

    fun loadDialerQueue() {
        val userKey = authDataStore.userKey ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            dialerRepository.getDialerData(userKey).let { result ->
                when (result) {
                    is com.crm.v2.common.result.Resource.Success -> _uiState.update {
                        it.copy(dialerQueue = result.data, isLoading = false, isRefreshing = false)
                    }
                    is com.crm.v2.common.result.Resource.Error -> _uiState.update {
                        it.copy(error = result.message, isLoading = false, isRefreshing = false)
                    }
                    else -> {}
                }
            }
        }
    }

    fun loadLeads(refresh: Boolean = false) {
        val userKey = authDataStore.userKey ?: return
        viewModelScope.launch {
            val page = if (refresh) 0 else _uiState.value.leadPageIndex
            _uiState.update { it.copy(isLoading = true, isRefreshing = refresh) }
            leadRepository.getFollowUpLeads(userKey, "L").let { result ->
                when (result) {
                    is com.crm.v2.common.result.Resource.Success -> {
                        val newLeads = if (refresh) result.data else _uiState.value.leadList + result.data
                        _uiState.update {
                            it.copy(
                                leadList = newLeads,
                                isLoading = false,
                                isRefreshing = false,
                                leadPageIndex = page + 1,
                                hasMoreLeads = result.data.isNotEmpty(),
                            )
                        }
                    }
                    is com.crm.v2.common.result.Resource.Error -> _uiState.update {
                        it.copy(error = result.message, isLoading = false, isRefreshing = false)
                    }
                    else -> {}
                }
            }
        }
    }

    fun refreshLeads() {
        loadLeads(refresh = true)
    }

    fun refreshDialerQueue() {
        _uiState.update { it.copy(isRefreshing = true) }
        loadDialerQueue()
    }

    fun startAutoDial() {
        val userKey = authDataStore.userKey ?: return
        _uiState.update { it.copy(isAutoDialing = true) }

        autoDialJob?.cancel()
        autoDialJob = viewModelScope.launch {
            telephonyManager.register(TelephonyConfig(
                    providerType = com.crm.v2.telephony.ProviderType.PJSIP,
                    sipServer = ApiConstants.Sip.SERVER,
                    sipUsername = ApiConstants.Sip.USERNAME,
                    sipPassword = ApiConstants.Sip.PASSWORD,
                ))
            dialerRepository.startAutoDial(userKey).collect { queue ->
                _uiState.update { it.copy(dialerQueue = queue) }
                if (queue.isNotEmpty()) {
                    callLead(queue[0])
                }
            }
        }
    }

    fun stopAutoDial() {
        autoDialJob?.cancel()
        autoDialJob = null
        lastCalledIndex = -1
        _uiState.update { it.copy(isAutoDialing = false, currentLead = null, showUpdateStatus = false, isMuted = false, isSpeakerOn = false, isOnHold = false) }
        viewModelScope.launch {
            if (telephonyManager.getCurrentState().isActive) {
                telephonyManager.hangup()
            }
        }
    }

    fun manualCall(number: String) {
        val trimmed = number.trim()
        if (trimmed.length < 6) {
            _uiState.update { it.copy(error = "Enter valid number") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null, lastCallMessage = null) }
            try {
                telephonyManager.register(TelephonyConfig(
                    providerType = com.crm.v2.telephony.ProviderType.PJSIP,
                    sipServer = ApiConstants.Sip.SERVER,
                    sipUsername = ApiConstants.Sip.USERNAME,
                    sipPassword = ApiConstants.Sip.PASSWORD,
                ))
                telephonyManager.call(trimmed)
                _uiState.update { it.copy(isLoading = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = "Call failed: ${e.message}", isLoading = false) }
            }
        }
    }

    fun callContact(contact: Contact) {
        val number = contact.phoneNumbers.firstOrNull() ?: return
        _uiState.update { it.copy(currentLead = DialerRecord(key = "", clientName = contact.name ?: "", contact = number)) }
        manualCall(number)
    }

    fun callManualNumber(number: String) {
        val trimmed = number.trim()
        _uiState.update {
            it.copy(
                currentLead = DialerRecord(key = "", clientName = "", contact = trimmed),
                callDuration = 0,
                showUpdateStatus = false,
                isMuted = false,
                isSpeakerOn = false,
                isOnHold = false,
            )
        }
        manualCall(trimmed)
    }

    fun callLead(lead: DialerRecord) {
        lastCalledIndex = _uiState.value.dialerQueue.indexOfFirst { it.key == lead.key }
        _uiState.update { it.copy(currentLead = lead, showUpdateStatus = false, callDuration = 0) }
        callStartTime = 0L
        manualCall(lead.contact)
    }

    fun hangup() {
        viewModelScope.launch {
            telephonyManager.hangup()
        }
    }

    fun dismissUpdateStatus() {
        _uiState.update { it.copy(showUpdateStatus = false, currentLead = null, callDuration = 0) }
    }

    fun getUserKey(): String = authDataStore.userKey ?: ""

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }

    fun toggleMute() {
        viewModelScope.launch {
            val newMuted = !_uiState.value.isMuted
            telephonyManager.mute(newMuted)
            _uiState.update { it.copy(isMuted = newMuted) }
        }
    }

    fun toggleSpeaker() {
        viewModelScope.launch {
            val newSpeaker = !_uiState.value.isSpeakerOn
            telephonyManager.speaker(newSpeaker)
            _uiState.update { it.copy(isSpeakerOn = newSpeaker) }
        }
    }

    fun toggleHold() {
        viewModelScope.launch {
            if (_uiState.value.isOnHold) {
                telephonyManager.resume()
                _uiState.update { it.copy(isOnHold = false) }
            } else {
                telephonyManager.hold()
                _uiState.update { it.copy(isOnHold = true) }
            }
        }
    }

    private fun autoDialNext() {
        autoDialJob?.cancel()
        autoDialJob = viewModelScope.launch {
            val queue = _uiState.value.dialerQueue
            val nextIndex = lastCalledIndex + 1

            if (nextIndex < queue.size) {
                delay(_uiState.value.autoDialInterval * 1000L)
                val lead = queue[nextIndex]
                _uiState.update { it.copy(currentLead = lead, showUpdateStatus = false) }
                manualCall(lead.contact)
            } else {
                _uiState.update { it.copy(isAutoDialing = false, currentLead = null, showUpdateStatus = false) }
            }
        }
    }

    fun submitStatus(
        key: String,
        status: String,
        callbackDate: String?,
        remarks: String?,
        userKey: String,
        callDuration: Int = 0,
        isHLLAPBL: String = "",
        isCibilBad: Boolean = false,
        isRealEstate: Boolean = false,
    ) {
        val shouldContinue = _uiState.value.isAutoDialing
        viewModelScope.launch {
            _uiState.update { it.copy(isSaving = true) }
            try {
                // TODO(manual): for manual leads (key == ""), server should be
                // Dialer/lead-info-new (create), not updateDialer. Revisit when wiring
                // the API contract.
                dialerRepository.updateDialer(
                    key = key,
                    status = status,
                    callbackDate = callbackDate,
                    remarks = remarks,
                    userKey = userKey,
                )
            } catch (e: Exception) {
                _uiState.update { it.copy(error = "Failed to save: ${e.message}", isSaving = false) }
            }
            _uiState.update { it.copy(showUpdateStatus = false, currentLead = null, callDuration = 0, isSaving = false) }
            if (shouldContinue) {
                val userK = authDataStore.userKey ?: return@launch
                dialerRepository.getDialerData(userK).let { result ->
                    when (result) {
                        is com.crm.v2.common.result.Resource.Success -> {
                            _uiState.update { it.copy(dialerQueue = result.data) }
                            autoDialNext()
                        }
                        is com.crm.v2.common.result.Resource.Error -> {
                            _uiState.update { it.copy(error = result.message, isAutoDialing = false) }
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        autoDialJob?.cancel()
        contactJob?.cancel()
    }
}
