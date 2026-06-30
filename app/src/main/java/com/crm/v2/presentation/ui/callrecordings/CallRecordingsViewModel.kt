package com.crm.v2.presentation.ui.callrecordings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crm.v2.common.result.Resource
import com.crm.v2.data.datastore.AuthDataStore
import com.crm.v2.domain.model.Document
import com.crm.v2.domain.repository.DocumentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CallRecordingsUiState(
    val recordings: List<Document> = emptyList(),
    val isLoading: Boolean = false,
)

@HiltViewModel
class CallRecordingsViewModel @Inject constructor(
    private val documentRepository: DocumentRepository,
    private val authDataStore: AuthDataStore,
) : ViewModel() {

    private val _uiState = MutableStateFlow(CallRecordingsUiState())
    val uiState: StateFlow<CallRecordingsUiState> = _uiState.asStateFlow()

    fun load() {
        val userKey = authDataStore.userKey ?: return
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            when (val result = documentRepository.getClientDocuments(userKey, "")) {
                is Resource.Success -> {
                    val recordings = result.data.filter { d -> d.fileType?.contains("recording", ignoreCase = true) == true }
                    _uiState.update { it.copy(recordings = recordings, isLoading = false) }
                }
                is Resource.Error -> _uiState.update { it.copy(isLoading = false) }
                else -> {}
            }
        }
    }
}
