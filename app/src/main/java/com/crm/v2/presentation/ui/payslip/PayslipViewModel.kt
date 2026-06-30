package com.crm.v2.presentation.ui.payslip

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crm.v2.common.result.Resource
import com.crm.v2.data.datastore.AuthDataStore
import com.crm.v2.domain.model.Document
import com.crm.v2.domain.repository.DocumentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PayslipUiState(
    val slips: List<Document> = emptyList(),
    val isLoading: Boolean = false,
)

@HiltViewModel
class PayslipViewModel @Inject constructor(
    private val documentRepository: DocumentRepository,
    private val authDataStore: AuthDataStore,
) : ViewModel() {

    private val _uiState = MutableStateFlow(PayslipUiState())
    val uiState: StateFlow<PayslipUiState> = _uiState.asStateFlow()

    fun load() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            when (val result = documentRepository.getPayslips(authDataStore.userKey ?: "")) {
                is Resource.Success -> _uiState.update { it.copy(slips = result.data, isLoading = false) }
                is Resource.Error -> _uiState.update { it.copy(isLoading = false) }
                else -> {}
            }
        }
    }
}
