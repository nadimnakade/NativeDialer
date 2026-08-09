package com.crm.v2.presentation.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crm.v2.common.result.Resource
import com.crm.v2.data.datastore.AuthDataStore
import com.crm.v2.domain.model.DashboardData
import com.crm.v2.domain.model.LiveCount
import com.crm.v2.domain.repository.ReportingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DashboardUiState(
    val dashboardData: DashboardData = DashboardData(),
    val liveCount: LiveCount = LiveCount(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val employeeName: String = "",
)

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val reportingRepository: ReportingRepository,
    private val authDataStore: AuthDataStore,
) : ViewModel() {

    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    init {
        _uiState.update { it.copy(employeeName = authDataStore.employeeName ?: "") }
        loadDashboard()
    }

    fun loadDashboard() {
        val userKey = authDataStore.userKey ?: return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            val errors = mutableListOf<String>()

            reportingRepository.getDashboardData(userKey).let { result ->
                when (result) {
                    is Resource.Success -> _uiState.update {
                        it.copy(dashboardData = result.data)
                    }
                    is Resource.Error -> errors.add(result.message ?: "Dashboard data failed")
                    else -> {}
                }
            }

            reportingRepository.getDialerLiveIndv(userKey).let { result ->
                when (result) {
                    is Resource.Success -> _uiState.update {
                        it.copy(liveCount = result.data)
                    }
                    is Resource.Error -> errors.add(result.message ?: "Live count failed")
                    else -> {}
                }
            }

            _uiState.update {
                it.copy(
                    isLoading = false,
                    error = errors.firstOrNull(),
                )
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}
