package com.crm.v2.presentation.ui.ringingcalls

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crm.v2.common.result.Resource
import com.crm.v2.data.datastore.AuthDataStore
import com.crm.v2.domain.model.DialerRecord
import com.crm.v2.domain.repository.LeadRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RingingCallsUiState(
    val leads: List<DialerRecord> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)

@HiltViewModel
class RingingCallsViewModel @Inject constructor(
    private val leadRepository: LeadRepository,
    private val authDataStore: AuthDataStore,
) : ViewModel() {

    private val _uiState = MutableStateFlow(RingingCallsUiState())
    val uiState: StateFlow<RingingCallsUiState> = _uiState.asStateFlow()

    init { load() }

    fun load() {
        val userKey = authDataStore.userKey ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            when (val result = leadRepository.getFollowUpLeads(userKey, "R")) {
                is Resource.Success -> _uiState.update { it.copy(leads = result.data, isLoading = false) }
                is Resource.Error -> _uiState.update { it.copy(error = result.message, isLoading = false) }
                else -> {}
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}
