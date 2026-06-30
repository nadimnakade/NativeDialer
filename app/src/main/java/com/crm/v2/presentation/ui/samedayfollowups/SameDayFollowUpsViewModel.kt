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
)

@HiltViewModel
class SameDayFollowUpsViewModel @Inject constructor(
    private val dialerRepository: DialerRepository,
    private val authDataStore: AuthDataStore,
) : ViewModel() {
    private val _uiState = MutableStateFlow(SameDayFollowUpsUiState())
    val uiState: StateFlow<SameDayFollowUpsUiState> = _uiState.asStateFlow()

    fun load() {
        val userKey = authDataStore.userKey ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            dialerRepository.getDialerNotUpdated(userKey).let { result ->
                val filtered = when (result) {
                    is Resource.Success -> result.data.filter { it.status == "SAME_DAY_FOLLOW_UP" || it.status == "1" }
                    else -> emptyList()
                }
                _uiState.update { it.copy(leads = filtered, isLoading = false) }
            }
        }
    }
}
