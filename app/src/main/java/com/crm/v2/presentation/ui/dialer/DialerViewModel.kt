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
    val followUpRecords: List<DialerRecord> = emptyList(),
    val sameDayRecords: List<DialerRecord> = emptyList(),
    val ringingRecords: List<DialerRecord> = emptyList(),
    val oneMinRecords: List<DialerRecord> = emptyList(),
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
    val callType: String = "A",
    val callDate: String = "0",
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

    init {
        viewModelScope.launch {
            telephonyManager.callState.collect { state ->
                val wasActive = _uiState.value.callState.isActive
                val nowIdle = state is CallState.Idle
                _uiState.update { it.copy(callState = state) }

                if (wasActive && nowIdle && _uiState.value.currentLead != null) {
                    val lead = _uiState.value.currentLead!!
                    val startTime = callStartTime
                    val duration = if (startTime > 0) ((System.currentTimeMillis() - startTime) / 1000).toInt() else 0
                    callStartTime = 0L
                    val cal = java.util.Calendar.getInstance()
                    if (startTime > 0) cal.timeInMillis = startTime
                    val callDateStr = "%02d/%02d/%04d %02d:%02d:%02d".format(
                        cal.get(java.util.Calendar.DAY_OF_MONTH),
                        cal.get(java.util.Calendar.MONTH) + 1,
                        cal.get(java.util.Calendar.YEAR),
                        cal.get(java.util.Calendar.HOUR_OF_DAY),
                        cal.get(java.util.Calendar.MINUTE),
                        cal.get(java.util.Calendar.SECOND),
                    )
                    _uiState.update {
                        it.copy(
                            callDuration = duration,
                            callDate = callDateStr,
                            showUpdateStatus = true,
                            isMuted = false,
                            isSpeakerOn = false,
                            isOnHold = false,
                            isLoading = false,
                            phoneNumber = "",
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
                        _uiState.update { it.copy(lastCallMessage = "Call connected", isLoading = false, phoneNumber = "") }
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
        _uiState.update { it.copy(activeTab = tab, phoneNumber = if (tab != 1) "" else it.phoneNumber) }
        when (tab) {
            0 -> loadDialerQueue()
            2 -> loadLeads(refresh = false)
            3 -> loadFollowUp()
            4 -> loadSameDayFollowUp()
            5 -> loadRinging()
            6 -> loadOneMin()
            7 -> loadContacts()
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

    private fun loadFollowUp() {
        val userKey = authDataStore.userKey ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            when (val result = leadRepository.getFollowUpLeads(userKey, "F")) {
                is com.crm.v2.common.result.Resource.Success -> _uiState.update {
                    it.copy(followUpRecords = result.data, isLoading = false)
                }
                is com.crm.v2.common.result.Resource.Error -> _uiState.update {
                    it.copy(error = result.message, isLoading = false)
                }
                else -> {}
            }
        }
    }

    private fun loadSameDayFollowUp() {
        val userKey = authDataStore.userKey ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            when (val result = leadRepository.getFollowUpLeads(userKey, "SF")) {
                is com.crm.v2.common.result.Resource.Success -> _uiState.update {
                    it.copy(sameDayRecords = result.data, isLoading = false)
                }
                is com.crm.v2.common.result.Resource.Error -> _uiState.update {
                    it.copy(error = result.message, isLoading = false)
                }
                else -> {}
            }
        }
    }

    private fun loadRinging() {
        val userKey = authDataStore.userKey ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            when (val result = leadRepository.getFollowUpLeads(userKey, "R")) {
                is com.crm.v2.common.result.Resource.Success -> _uiState.update {
                    it.copy(ringingRecords = result.data, isLoading = false)
                }
                is com.crm.v2.common.result.Resource.Error -> _uiState.update {
                    it.copy(error = result.message, isLoading = false)
                }
                else -> {}
            }
        }
    }

    private fun loadOneMin() {
        val userKey = authDataStore.userKey ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            when (val result = leadRepository.getFollowUpLeads(userKey, "1MIN")) {
                is com.crm.v2.common.result.Resource.Success -> _uiState.update {
                    it.copy(oneMinRecords = result.data, isLoading = false)
                }
                is com.crm.v2.common.result.Resource.Error -> _uiState.update {
                    it.copy(error = result.message, isLoading = false)
                }
                else -> {}
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
        _uiState.update { it.copy(currentLead = DialerRecord(key = "", clientName = contact.name ?: "", contact = number), callType = "M") }
        manualCall(number)
    }

    fun callManualNumber(number: String) {
        val trimmed = number.trim()
        _uiState.update {
            it.copy(
                currentLead = DialerRecord(key = "", clientName = "", contact = trimmed),
                callDuration = 0,
                callType = "M",
                showUpdateStatus = false,
                isMuted = false,
                isSpeakerOn = false,
                isOnHold = false,
            )
        }
        manualCall(trimmed)
    }

    fun callLead(lead: DialerRecord) {
        val tabType = when (_uiState.value.activeTab) {
            0 -> "A"
            3 -> "F"
            4 -> "SF"
            5 -> "R"
            6 -> "1MIN"
            else -> "A"
        }
        _uiState.update { it.copy(currentLead = lead, showUpdateStatus = false, callDuration = 0, callType = tabType) }
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

    fun clearLastCallMessage() {
        _uiState.update { it.copy(lastCallMessage = null) }
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
            val lead = queue.firstOrNull()

            if (lead != null) {
                delay(_uiState.value.autoDialInterval * 1000L)
                _uiState.update { it.copy(currentLead = lead, showUpdateStatus = false, callDuration = 0) }
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
        callbackTime: String?,
        remarks: String?,
        userKey: String,
        callDuration: Int = 0,
        callDate: String = "0",
        callType: String = "A",
        clientName: String = "",
        mobileNo: String = "",
        salary: String = "",
        company: String = "",
        isHLLAPBL: Int = 0,
        isCibilBad: Boolean = false,
        isRealEstate: Boolean = false,
    ) {
        val shouldContinue = _uiState.value.isAutoDialing
        viewModelScope.launch {
            _uiState.update { it.copy(isSaving = true) }
            try {
                val durationStr = "${callDuration}@|0"
                val result = if (callType == "M") {
                    dialerRepository.insertLeadInfoNew(
                        clientName = clientName,
                        contact = mobileNo,
                        salary = salary,
                        company = company,
                        remarks = remarks ?: "",
                        status = status,
                        callbackDate = callbackDate ?: "",
                        callbackTime = callbackTime ?: "",
                        callerDuration = durationStr,
                        callDateTime = callDate,
                        userKey = userKey,
                        isHllapbl = isHLLAPBL,
                        isCibilBad = if (isCibilBad) 1 else 0,
                        isRealEstate = if (isRealEstate) 1 else 0,
                    )
                } else {
                    dialerRepository.updateDialer(
                        key = key,
                        status = status,
                        callbackDate = callbackDate,
                        callbackTime = callbackTime,
                        remarks = remarks,
                        userKey = userKey,
                        callDuration = durationStr,
                        callDate = callDate,
                        isHllapbl = isHLLAPBL,
                        isCibilBad = if (isCibilBad) 1 else 0,
                        isRealEstate = if (isRealEstate) 1 else 0,
                    )
                }
                when (result) {
                    is com.crm.v2.common.result.Resource.Success -> {
                        _uiState.update { it.copy(lastCallMessage = "Status saved: $status", isSaving = false) }
                    }
                    is com.crm.v2.common.result.Resource.Error -> {
                        _uiState.update { it.copy(error = "Save failed: ${result.message}", isSaving = false) }
                    }
                    else -> {}
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = "Failed to save: ${e.message}", isSaving = false) }
            }
            _uiState.update { it.copy(showUpdateStatus = false, currentLead = null, callDuration = 0) }
            reloadSourceTab(callType, shouldContinue)
        }
    }

    private fun reloadSourceTab(callType: String, shouldContinue: Boolean) {
        val userKey = authDataStore.userKey ?: return
        when (callType) {
            "F" -> refreshFollowUpTab(userKey, "F") { list -> _uiState.update { it.copy(followUpRecords = list) } }
            "SF" -> refreshFollowUpTab(userKey, "SF") { list -> _uiState.update { it.copy(sameDayRecords = list) } }
            "R" -> refreshFollowUpTab(userKey, "R") { list -> _uiState.update { it.copy(ringingRecords = list) } }
            "1MIN" -> refreshFollowUpTab(userKey, "1MIN") { list -> _uiState.update { it.copy(oneMinRecords = list) } }
            "M" -> {}
            else -> {
                if (_uiState.value.activeTab == 2) {
                    loadLeads(refresh = true)
                } else {
                    viewModelScope.launch {
                        dialerRepository.getDialerData(userKey).let { result ->
                            when (result) {
                                is com.crm.v2.common.result.Resource.Success -> {
                                    _uiState.update { it.copy(dialerQueue = result.data) }
                                    if (shouldContinue) autoDialNext()
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
        }
    }

    private fun refreshFollowUpTab(userKey: String, type: String, onSuccess: (List<DialerRecord>) -> Unit) {
        viewModelScope.launch {
            when (val result = leadRepository.getFollowUpLeads(userKey, type)) {
                is com.crm.v2.common.result.Resource.Success -> onSuccess(result.data)
                is com.crm.v2.common.result.Resource.Error -> _uiState.update { it.copy(error = result.message) }
                else -> {}
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        autoDialJob?.cancel()
        contactJob?.cancel()
    }
}
