package com.crm.v2.presentation.ui.reports

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crm.v2.common.result.Resource
import com.crm.v2.data.datastore.AuthDataStore
import com.crm.v2.domain.model.AttendanceData
import com.crm.v2.domain.model.DashboardData
import com.crm.v2.domain.model.LiveCount
import com.crm.v2.domain.model.PerformanceData
import com.crm.v2.domain.repository.ReportingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ReportsUiState(
    val activeTab: Int = 0,
    val performance: List<PerformanceData> = emptyList(),
    val attendance: AttendanceData = AttendanceData(),
    val dialerLive: LiveCount = LiveCount(),
    val isLoading: Boolean = false,
    val error: String? = null,
)

@HiltViewModel
class ReportsViewModel @Inject constructor(
    private val reportingRepository: ReportingRepository,
    private val authDataStore: AuthDataStore,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ReportsUiState())
    val uiState: StateFlow<ReportsUiState> = _uiState.asStateFlow()

    init {
        loadAll()
    }

    fun setActiveTab(tab: Int) = _uiState.update { it.copy(activeTab = tab) }

    fun loadAll() {
        val userKey = authDataStore.userKey ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                reportingRepository.getPerformance(userKey).let { result ->
                    if (result is Resource.Success) _uiState.update { it.copy(performance = result.data) }
                    if (result is Resource.Error) _uiState.update { it.copy(error = result.message) }
                }
                reportingRepository.getAttendance(userKey).let { result ->
                    if (result is Resource.Success) _uiState.update { it.copy(attendance = result.data) }
                }
                reportingRepository.getDialerLiveIndv(userKey).let { result ->
                    if (result is Resource.Success) _uiState.update { it.copy(dialerLive = result.data) }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message ?: "Failed to load reports") }
            }
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}
