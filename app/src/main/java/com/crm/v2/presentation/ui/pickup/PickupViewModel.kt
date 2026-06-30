package com.crm.v2.presentation.ui.pickup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crm.v2.common.result.Resource
import com.crm.v2.data.datastore.AuthDataStore
import com.crm.v2.domain.model.PickupEntry
import com.crm.v2.domain.repository.PickupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PickupListUiState(
    val entries: List<PickupEntry> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
)

@HiltViewModel
class PickupViewModel @Inject constructor(
    private val pickupRepository: PickupRepository,
    private val authDataStore: AuthDataStore,
) : ViewModel() {

    private val _uiState = MutableStateFlow(PickupListUiState())
    val uiState: StateFlow<PickupListUiState> = _uiState.asStateFlow()

    init { loadPickups() }

    fun loadPickups() {
        val userKey = authDataStore.userKey ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            when (val result = pickupRepository.getPickupEntryData(userKey, null)) {
                is Resource.Success -> _uiState.update { it.copy(entries = result.data, isLoading = false) }
                is Resource.Error -> _uiState.update { it.copy(error = result.message, isLoading = false) }
                else -> {}
            }
        }
    }

    fun onSearchQueryChanged(query: String) = _uiState.update { it.copy(searchQuery = query) }
}
