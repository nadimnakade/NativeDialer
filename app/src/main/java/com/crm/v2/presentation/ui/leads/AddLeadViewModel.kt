package com.crm.v2.presentation.ui.leads

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crm.v2.common.result.Resource
import com.crm.v2.data.datastore.AuthDataStore
import com.crm.v2.domain.repository.LeadRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AddLeadUiState(
    val name: String = "",
    val mobileNo: String = "",
    val product: String = "",
    val callerId: String = "",
    val remarks: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false,
)

@HiltViewModel
class AddLeadViewModel @Inject constructor(
    private val leadRepository: LeadRepository,
    private val authDataStore: AuthDataStore,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddLeadUiState())
    val uiState: StateFlow<AddLeadUiState> = _uiState.asStateFlow()

    fun onNameChanged(value: String) = _uiState.update { it.copy(name = value, error = null) }
    fun onMobileChanged(value: String) = _uiState.update { it.copy(mobileNo = value.filter { c -> c.isDigit() }.take(10), error = null) }
    fun onProductChanged(value: String) = _uiState.update { it.copy(product = value) }
    fun onRemarksChanged(value: String) = _uiState.update { it.copy(remarks = value) }

    fun submit() {
        val state = _uiState.value
        if (state.name.isBlank()) { _uiState.update { it.copy(error = "Name is required") }; return }
        if (state.mobileNo.length != 10) { _uiState.update { it.copy(error = "Valid 10-digit mobile required") }; return }
        if (state.product.isBlank()) { _uiState.update { it.copy(error = "Product is required") }; return }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            when (val result = leadRepository.createLead(
                name = state.name,
                mobileNo = state.mobileNo,
                product = state.product,
                callerId = state.callerId.ifBlank { null },
                remarks = state.remarks.ifBlank { null },
                userKey = authDataStore.userKey ?: "",
            )) {
                is Resource.Success -> _uiState.update { it.copy(isLoading = false, isSuccess = true) }
                is Resource.Error -> _uiState.update { it.copy(isLoading = false, error = result.message) }
                else -> {}
            }
        }
    }
}
