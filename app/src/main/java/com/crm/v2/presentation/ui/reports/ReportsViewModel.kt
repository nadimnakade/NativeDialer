package com.crm.v2.presentation.ui.reports

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crm.v2.common.result.Resource
import com.crm.v2.data.datastore.AuthDataStore
import com.crm.v2.domain.model.AttendanceData
import com.crm.v2.domain.model.LiveCount
import com.crm.v2.domain.model.PerformanceData
import com.crm.v2.domain.repository.ReportingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ReportsUiState(
    val activeTab: Int = 0,
    val performance: List<PerformanceData> = emptyList(),
    val attendance: AttendanceData = AttendanceData(),
    val dialerLive: LiveCount = LiveCount(),
    val isLoadingPerformance: Boolean = false,
    val isLoadingAttendance: Boolean = false,
    val isLoadingLiveCount: Boolean = false,
    val error: String? = null,
    val isAutoRefreshing: Boolean = false,
    val nextRefreshInSeconds: Int = 60,
)

@HiltViewModel
class ReportsViewModel @Inject constructor(
    private val reportingRepository: ReportingRepository,
    private val authDataStore: AuthDataStore,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ReportsUiState())
    val uiState: StateFlow<ReportsUiState> = _uiState.asStateFlow()
    private var autoRefreshJob: Job? = null
    private var countdownJob: Job? = null

    private val loadedTabs = mutableSetOf<Int>()

    fun setActiveTab(tab: Int) {
        _uiState.update { it.copy(activeTab = tab) }
        if (tab !in loadedTabs) {
            when (tab) {
                0 -> loadPerformance()
                1 -> loadAttendance()
                2 -> loadLiveCount()
            }
        }
        if (tab == 2) startAutoRefresh() else stopAutoRefresh()
    }

    private fun loadPerformance() {
        val userKey = authDataStore.userKey ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoadingPerformance = true, error = null) }
            reportingRepository.getPerformance(userKey).let { result ->
                if (result is Resource.Success) {
                    _uiState.update { it.copy(performance = result.data) }
                    loadedTabs.add(0)
                }
                if (result is Resource.Error) _uiState.update { it.copy(error = result.message) }
            }
            _uiState.update { it.copy(isLoadingPerformance = false) }
        }
    }

    private fun loadAttendance() {
        val userKey = authDataStore.userKey ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoadingAttendance = true, error = null) }
            reportingRepository.getAttendance(userKey).let { result ->
                if (result is Resource.Success) {
                    _uiState.update { it.copy(attendance = result.data) }
                    loadedTabs.add(1)
                }
                if (result is Resource.Error) _uiState.update { it.copy(error = result.message) }
            }
            _uiState.update { it.copy(isLoadingAttendance = false) }
        }
    }

    private fun loadLiveCount() {
        val userKey = authDataStore.userKey ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoadingLiveCount = true, error = null) }
            reportingRepository.getDialerLiveIndv(userKey).let { result ->
                if (result is Resource.Success) {
                    _uiState.update { it.copy(dialerLive = result.data) }
                    loadedTabs.add(2)
                }
                if (result is Resource.Error) _uiState.update { it.copy(error = result.message) }
            }
            _uiState.update { it.copy(isLoadingLiveCount = false) }
            if (_uiState.value.activeTab == 2) {
                startAutoRefresh()
            }
        }
    }

    fun refreshLiveCount() {
        val userKey = authDataStore.userKey ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(isAutoRefreshing = true) }
            reportingRepository.getDialerLiveIndv(userKey).let { result ->
                if (result is Resource.Success) _uiState.update { it.copy(dialerLive = result.data) }
            }
            _uiState.update { it.copy(isAutoRefreshing = false, nextRefreshInSeconds = 60) }
            startCountdown()
        }
    }

    fun pullToRefresh() {
        loadedTabs.clear()
        when (_uiState.value.activeTab) {
            0 -> loadPerformance()
            1 -> loadAttendance()
            2 -> loadLiveCount()
        }
    }

    private fun startAutoRefresh() {
        stopAutoRefresh()
        _uiState.update { it.copy(nextRefreshInSeconds = 60) }
        startCountdown()
        autoRefreshJob = viewModelScope.launch {
            while (isActive) {
                delay(60_000)
                refreshLiveCount()
            }
        }
    }

    private fun startCountdown() {
        countdownJob?.cancel()
        countdownJob = viewModelScope.launch {
            while (isActive && _uiState.value.nextRefreshInSeconds > 0) {
                delay(1_000)
                _uiState.update { it.copy(nextRefreshInSeconds = it.nextRefreshInSeconds - 1) }
            }
        }
    }

    private fun stopAutoRefresh() {
        autoRefreshJob?.cancel()
        autoRefreshJob = null
        countdownJob?.cancel()
        countdownJob = null
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }

    override fun onCleared() {
        super.onCleared()
        stopAutoRefresh()
    }
}
