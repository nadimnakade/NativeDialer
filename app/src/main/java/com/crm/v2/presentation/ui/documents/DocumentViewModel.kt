package com.crm.v2.presentation.ui.documents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crm.v2.common.result.Resource
import com.crm.v2.data.datastore.AuthDataStore
import com.crm.v2.domain.model.ClientInfo
import com.crm.v2.domain.model.Document
import com.crm.v2.domain.repository.DocumentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class DocumentUiState(
    val searchQuery: String = "",
    val clients: List<ClientInfo> = emptyList(),
    val selectedClient: ClientInfo? = null,
    val documents: List<Document> = emptyList(),
    val isSearching: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
)

@HiltViewModel
class DocumentViewModel @Inject constructor(
    private val documentRepository: DocumentRepository,
    private val authDataStore: AuthDataStore,
) : ViewModel() {

    private val _uiState = MutableStateFlow(DocumentUiState())
    val uiState: StateFlow<DocumentUiState> = _uiState.asStateFlow()

    fun onSearchQueryChanged(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun searchClients() {
        val query = _uiState.value.searchQuery
        if (query.length < 3) return
        val userKey = authDataStore.userKey ?: return

        viewModelScope.launch {
            _uiState.update { it.copy(isSearching = true, error = null) }
            when (val result = documentRepository.getCallerClients(userKey, query)) {
                is Resource.Success -> _uiState.update { it.copy(clients = result.data, isSearching = false) }
                is Resource.Error -> _uiState.update { it.copy(error = result.message, isSearching = false) }
                else -> {}
            }
        }
    }

    fun selectClient(client: ClientInfo) {
        _uiState.update { it.copy(selectedClient = client) }
        loadDocuments(client.mobileNo ?: "")
    }

    private fun loadDocuments(mobileNo: String) {
        val userKey = authDataStore.userKey ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            when (val result = documentRepository.getClientDocuments(userKey, mobileNo)) {
                is Resource.Success -> _uiState.update { it.copy(documents = result.data, isLoading = false) }
                is Resource.Error -> _uiState.update { it.copy(error = result.message, isLoading = false) }
                else -> {}
            }
        }
    }

    fun clearSelection() {
        _uiState.update { it.copy(selectedClient = null, documents = emptyList()) }
    }
}
