package com.crm.v2.presentation.ui.dailylogin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crm.v2.common.result.Resource
import com.crm.v2.data.datastore.AuthDataStore
import com.crm.v2.domain.model.DailyLogin
import com.crm.v2.domain.repository.DailyLoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DailyLoginUiState(
    val activeTab: Int = 0,
    val clientName: String = "",
    val mobileNo: String = "",
    val banks: String = "",
    val product: String = "",
    val region: String = "",
    val losNo: String = "",
    val appNo: String = "",
    val loginAmount: String = "",
    val remarks: String = "",
    val list: List<DailyLogin> = emptyList(),
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,
)

@HiltViewModel
class DailyLoginViewModel @Inject constructor(
    private val dailyLoginRepository: DailyLoginRepository,
    private val authDataStore: AuthDataStore,
) : ViewModel() {

    private val _uiState = MutableStateFlow(DailyLoginUiState())
    val uiState: StateFlow<DailyLoginUiState> = _uiState.asStateFlow()

    fun setActiveTab(tab: Int) = _uiState.update { it.copy(activeTab = tab) }
    fun onFieldChange(field: String, value: String) = _uiState.update { state ->
        when (field) {
            "clientName" -> state.copy(clientName = value)
            "mobileNo" -> state.copy(mobileNo = value.filter { c -> c.isDigit() }.take(10))
            "banks" -> state.copy(banks = value)
            "product" -> state.copy(product = value)
            "region" -> state.copy(region = value)
            "losNo" -> state.copy(losNo = value)
            "appNo" -> state.copy(appNo = value)
            "loginAmount" -> state.copy(loginAmount = value.filter { c -> c.isDigit() || c == '.' })
            "remarks" -> state.copy(remarks = value)
            else -> state
        }
    }

    fun submit() {
        val s = _uiState.value
        if (s.clientName.isBlank()) { _uiState.update { it.copy(error = "Client name required") }; return }
        if (s.mobileNo.length != 10) { _uiState.update { it.copy(error = "Valid 10-digit mobile required") }; return }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            when (val result = dailyLoginRepository.insertDailyLogin(
                clientName = s.clientName, mobileNo = s.mobileNo,
                banks = s.banks, product = s.product, region = s.region,
                losNo = s.losNo, appNo = s.appNo,
                loginAmt = s.loginAmount.toDoubleOrNull() ?: 0.0,
                remarks = s.remarks.ifBlank { null },
                userKey = authDataStore.userKey ?: "",
            )) {
                is Resource.Success -> _uiState.update {
                    it.copy(
                        isLoading = false, isSuccess = true,
                        clientName = "", mobileNo = "", banks = "", product = "",
                        region = "", losNo = "", appNo = "", loginAmount = "", remarks = "",
                    )
                }
                is Resource.Error -> _uiState.update { it.copy(isLoading = false, error = result.message) }
                else -> {}
            }
        }
    }

    fun loadList() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            when (val result = dailyLoginRepository.getDailyLoginList(authDataStore.userKey ?: "")) {
                is Resource.Success -> _uiState.update { it.copy(list = result.data, isLoading = false) }
                is Resource.Error -> _uiState.update { it.copy(error = result.message, isLoading = false) }
                else -> {}
            }
        }
    }
}
