package com.crm.v2.presentation.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crm.v2.common.result.Resource
import com.crm.v2.data.datastore.AuthDataStore
import com.crm.v2.domain.model.ClientInfo
import com.crm.v2.domain.repository.DocumentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SearchUiState(
    val query: String = "",
    val results: List<ClientInfo> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val documentRepository: DocumentRepository,
    private val authDataStore: AuthDataStore,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private var searchJob: Job? = null

    fun onErrorShown() { _uiState.update { it.copy(error = null) } }

    fun onQueryChange(query: String) {
        _uiState.update { it.copy(query = query) }
        searchJob?.cancel()
        if (query.trim().length > 3) {
            searchJob = viewModelScope.launch {
                delay(400)
                search(query.trim())
            }
        } else if (query.isEmpty()) {
            _uiState.update { it.copy(results = emptyList()) }
        }
    }

    private suspend fun search(query: String) {
        _uiState.update { it.copy(isLoading = true) }
        when (val result = documentRepository.getCallerClients(authDataStore.userKey ?: "", query)) {
            is Resource.Success -> _uiState.update { it.copy(results = result.data, isLoading = false) }
            is Resource.Error -> _uiState.update { it.copy(error = result.message, isLoading = false) }
            else -> {}
        }
    }
}
