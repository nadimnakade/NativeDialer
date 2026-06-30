package com.crm.v2.presentation.ui.leads

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crm.v2.common.result.Resource
import com.crm.v2.data.datastore.AuthDataStore
import com.crm.v2.domain.model.Lead
import com.crm.v2.domain.repository.LeadRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LeadDetailUiState(
    val lead: Lead? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)

@HiltViewModel
class LeadDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val leadRepository: LeadRepository,
    private val authDataStore: AuthDataStore,
) : ViewModel() {

    private val leadKey: String = savedStateHandle.get<String>("key") ?: ""

    private val _uiState = MutableStateFlow(LeadDetailUiState())
    val uiState: StateFlow<LeadDetailUiState> = _uiState.asStateFlow()

    init {
        loadLead()
    }

    fun loadLead() {
        val userKey = authDataStore.userKey ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            when (val result = leadRepository.getLeadDetail(userKey, leadKey)) {
                is Resource.Success -> _uiState.update { it.copy(lead = result.data, isLoading = false) }
                is Resource.Error -> _uiState.update { it.copy(error = result.message, isLoading = false) }
                else -> {}
            }
        }
    }
}
