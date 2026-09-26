package com.crm.v2.presentation.ui.searchapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crm.v2.common.result.Resource
import com.crm.v2.data.datastore.AuthDataStore
import com.crm.v2.domain.model.SearchApplication
import com.crm.v2.domain.repository.SearchApplicationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SearchApplicationUiState(
    val applications: List<SearchApplication> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null,
    val searchQuery: String = "",
    val totalCount: Int = 0,
    val currentPage: Int = 0,
    val hasMore: Boolean = true,
)

@HiltViewModel
class SearchApplicationViewModel @Inject constructor(
    private val repository: SearchApplicationRepository,
    private val authDataStore: AuthDataStore,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchApplicationUiState())
    val uiState: StateFlow<SearchApplicationUiState> = _uiState.asStateFlow()
    private var searchJob: Job? = null

    init {
        loadApplications()
    }

    fun loadApplications() {
        val userKey = authDataStore.userKey ?: return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null, currentPage = 0) }

            when (val result = repository.searchApplications(userKey, 0, 10, _uiState.value.searchQuery)) {
                is Resource.Success -> _uiState.update {
                    it.copy(
                        applications = result.data,
                        isLoading = false,
                        isRefreshing = false,
                        totalCount = result.data.size,
                        hasMore = result.data.size >= 10,
                    )
                }
                is Resource.Error -> _uiState.update {
                    it.copy(error = result.message, isLoading = false, isRefreshing = false)
                }
                else -> {}
            }
        }
    }

    fun loadMore() {
        if (_uiState.value.isLoading || !_uiState.value.hasMore) return

        val userKey = authDataStore.userKey ?: return
        val nextPage = _uiState.value.currentPage + 1

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            when (val result = repository.searchApplications(userKey, nextPage, 10, _uiState.value.searchQuery)) {
                is Resource.Success -> _uiState.update {
                    it.copy(
                        applications = it.applications + result.data,
                        isLoading = false,
                        currentPage = nextPage,
                        hasMore = result.data.size >= 10,
                    )
                }
                is Resource.Error -> _uiState.update {
                    it.copy(error = result.message, isLoading = false)
                }
                else -> {}
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(400)
            loadApplications()
        }
    }

    fun refresh() {
        _uiState.update { it.copy(isRefreshing = true) }
        loadApplications()
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}
