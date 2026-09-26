package com.crm.v2.presentation.ui.samedayfollowup

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

data class SameDayFollowUpUiState(
    val leads: List<DialerRecord> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)

@HiltViewModel
class SameDayFollowUpViewModel @Inject constructor(
    private val leadRepository: LeadRepository,
    private val authDataStore: AuthDataStore,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SameDayFollowUpUiState())
    val uiState: StateFlow<SameDayFollowUpUiState> = _uiState.asStateFlow()

    fun onErrorShown() { _uiState.update { it.copy(error = null) } }

    fun load() {
        val userKey = authDataStore.userKey ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            when (val result = leadRepository.getFollowUpLeads(userKey, "SF")) {
                is Resource.Success -> _uiState.update { it.copy(leads = result.data, isLoading = false) }
                is Resource.Error -> _uiState.update { it.copy(error = result.message, isLoading = false) }
                else -> {}
            }
        }
    }
}
