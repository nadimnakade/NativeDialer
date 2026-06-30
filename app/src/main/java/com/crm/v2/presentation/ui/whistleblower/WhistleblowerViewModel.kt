package com.crm.v2.presentation.ui.whistleblower

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crm.v2.common.result.Resource
import com.crm.v2.data.datastore.AuthDataStore
import com.crm.v2.domain.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class WhistleblowerUiState(
    val description: String = "",
    val department: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val success: String? = null,
)

@HiltViewModel
class WhistleblowerViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val authDataStore: AuthDataStore,
) : ViewModel() {

    private val _uiState = MutableStateFlow(WhistleblowerUiState())
    val uiState: StateFlow<WhistleblowerUiState> = _uiState.asStateFlow()

    fun onDescriptionChange(v: String) { _uiState.update { it.copy(description = v, error = null, success = null) } }
    fun onDepartmentChange(v: String) { _uiState.update { it.copy(department = v, error = null, success = null) } }

    fun submit() {
        val state = _uiState.value
        if (state.description.isBlank()) { _uiState.update { it.copy(error = "Please describe the issue") }; return }
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null, success = null) }
            val body = buildString {
                append("Whistleblower Report\n")
                append("Description: ${state.description}\n")
                if (state.department.isNotBlank()) append("Department: ${state.department}\n")
            }
            when (settingsRepository.sendFeedback(body, authDataStore.userKey ?: "")) {
                is Resource.Success -> _uiState.update { it.copy(isLoading = false, success = "Report submitted successfully", description = "", department = "") }
                is Resource.Error -> _uiState.update { it.copy(isLoading = false, error = "Failed to submit report") }
                else -> {}
            }
        }
    }
}
