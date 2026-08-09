package com.crm.v2.presentation.ui.leads

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crm.v2.common.result.Resource
import com.crm.v2.data.datastore.AuthDataStore
import com.crm.v2.domain.model.Lead
import com.crm.v2.domain.repository.LeadRepository
import com.crm.v2.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LeadListUiState(
    val leads: List<Lead> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val error: String? = null,
    val hasMore: Boolean = true,
    val currentPage: Int = 0,
)

@HiltViewModel
class LeadListViewModel @Inject constructor(
    private val leadRepository: LeadRepository,
    private val authDataStore: AuthDataStore,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LeadListUiState())
    val uiState: StateFlow<LeadListUiState> = _uiState.asStateFlow()

    init {
        loadLeads()
    }

    fun loadLeads() {
        val userKey = authDataStore.userKey ?: return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null, currentPage = 0) }

            when (val result = leadRepository.getLeads(userKey, 0)) {
                is Resource.Success -> _uiState.update {
                    it.copy(leads = result.data, isLoading = false, hasMore = result.data.size >= 20)
                }
                is Resource.Error -> _uiState.update {
                    it.copy(isLoading = false, error = result.message)
                }
                else -> {}
            }
        }
    }

    fun loadMore() {
        val userKey = authDataStore.userKey ?: return
        if (_uiState.value.isLoadingMore || !_uiState.value.hasMore) return

        viewModelScope.launch {
            val nextPage = _uiState.value.currentPage + 1
            _uiState.update { it.copy(isLoadingMore = true) }

            when (val result = leadRepository.getLeads(userKey, nextPage)) {
                is Resource.Success -> _uiState.update {
                    it.copy(
                        leads = it.leads + result.data,
                        isLoadingMore = false,
                        currentPage = nextPage,
                        hasMore = result.data.size >= 20,
                    )
                }
                is Resource.Error -> _uiState.update { it.copy(isLoadingMore = false) }
                else -> {}
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}
