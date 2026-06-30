package com.crm.v2.presentation.ui.agentstatus

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crm.v2.common.result.Resource
import com.crm.v2.data.datastore.AuthDataStore
import com.crm.v2.domain.model.CallLog
import com.crm.v2.domain.repository.ReportingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AgentStatusUiState(
    val agents: List<CallLog> = emptyList(),
    val isLoading: Boolean = false,
)

@HiltViewModel
class AgentStatusViewModel @Inject constructor(
    private val reportingRepository: ReportingRepository,
    private val authDataStore: AuthDataStore,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AgentStatusUiState())
    val uiState: StateFlow<AgentStatusUiState> = _uiState.asStateFlow()

    fun load() {
        val userKey = authDataStore.userKey ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            when (val result = reportingRepository.getTeamCallLog(userKey)) {
                is Resource.Success -> _uiState.update { it.copy(agents = result.data, isLoading = false) }
                is Resource.Error -> _uiState.update { it.copy(isLoading = false) }
                else -> {}
            }
        }
    }
}
