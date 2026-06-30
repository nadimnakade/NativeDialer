package com.crm.v2.presentation.ui.ringingcalls

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crm.v2.common.result.Resource
import com.crm.v2.data.datastore.AuthDataStore
import com.crm.v2.domain.model.DialerRecord
import com.crm.v2.domain.repository.DialerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RingingCallsUiState(
    val leads: List<DialerRecord> = emptyList(),
    val isLoading: Boolean = false,
)

@HiltViewModel
class RingingCallsViewModel @Inject constructor(
    private val dialerRepository: DialerRepository,
    private val authDataStore: AuthDataStore,
) : ViewModel() {

    private val _uiState = MutableStateFlow(RingingCallsUiState())
    val uiState: StateFlow<RingingCallsUiState> = _uiState.asStateFlow()

    fun load() {
        val userKey = authDataStore.userKey ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            dialerRepository.getDialerNotUpdated(userKey).let { result ->
                val filtered = when (result) {
                    is Resource.Success -> result.data.filter { it.status == "RINGING" || it.status == "0" }
                    else -> emptyList()
                }
                _uiState.update { it.copy(leads = filtered, isLoading = false) }
            }
        }
    }
}
