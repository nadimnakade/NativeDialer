package com.crm.v2.presentation.ui.samedayfollowups

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

data class SameDayFollowUpsUiState(
    val leads: List<DialerRecord> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)

@HiltViewModel
class SameDayFollowUpsViewModel @Inject constructor(
    private val dialerRepository: DialerRepository,
    private val authDataStore: AuthDataStore,
) : ViewModel() {
    private val _uiState = MutableStateFlow(SameDayFollowUpsUiState())
    val uiState: StateFlow<SameDayFollowUpsUiState> = _uiState.asStateFlow()

    fun onErrorShown() { _uiState.update { it.copy(error = null) } }

    fun load() {
        val userKey = authDataStore.userKey ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            dialerRepository.getDialerNotUpdated(userKey).let { result ->
                when (result) {
                    is Resource.Success -> {
                        val filtered = result.data.filter { it.status == "SameDay Follow Up" || it.status == "SF" || it.status == "1" }
                        _uiState.update { it.copy(leads = filtered, isLoading = false) }
                    }
                    is Resource.Error -> _uiState.update { it.copy(error = result.message, isLoading = false) }
                    else -> {}
                }
            }
        }
    }
}
