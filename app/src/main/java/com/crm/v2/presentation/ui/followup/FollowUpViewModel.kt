package com.crm.v2.presentation.ui.followup

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

data class FollowUpUiState(
    val activeTab: Int = 0,
    val records: List<DialerRecord> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)

@HiltViewModel
class FollowUpViewModel @Inject constructor(
    private val leadRepository: LeadRepository,
    private val authDataStore: AuthDataStore,
) : ViewModel() {

    private val _uiState = MutableStateFlow(FollowUpUiState())
    val uiState: StateFlow<FollowUpUiState> = _uiState.asStateFlow()

    private val followUpTypes = listOf("F", "SF", "1MIN", "R")

    init { loadRecords() }

    fun setActiveTab(tab: Int) {
        _uiState.update { it.copy(activeTab = tab) }
        loadRecords()
    }

    fun loadRecords() {
        val userKey = authDataStore.userKey ?: return
        val type = followUpTypes.getOrNull(_uiState.value.activeTab) ?: "F"

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            when (val result = leadRepository.getFollowUpLeads(userKey, type)) {
                is Resource.Success -> _uiState.update { it.copy(records = result.data, isLoading = false) }
                is Resource.Error -> _uiState.update { it.copy(error = result.message, isLoading = false) }
                else -> {}
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}
