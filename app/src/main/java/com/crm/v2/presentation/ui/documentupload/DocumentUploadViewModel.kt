package com.crm.v2.presentation.ui.documentupload

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crm.v2.data.datastore.AuthDataStore
import com.crm.v2.domain.repository.DocumentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

data class DocumentUploadUiState(
    val clientName: String = "",
    val mobileNo: String = "",
    val fileType: String = "",
    val selectedFilePath: String = "",
    val selectedFileName: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val success: String? = null,
)

@HiltViewModel
class DocumentUploadViewModel @Inject constructor(
    private val documentRepository: DocumentRepository,
    private val authDataStore: AuthDataStore,
    private val app: Application,
) : ViewModel() {

    private val _uiState = MutableStateFlow(DocumentUploadUiState())
    val uiState: StateFlow<DocumentUploadUiState> = _uiState.asStateFlow()

    fun onClientNameChange(v: String) { _uiState.update { it.copy(clientName = v, error = null, success = null) } }
    fun onMobileNoChange(v: String) { _uiState.update { it.copy(mobileNo = v, error = null, success = null) } }
    fun onFileTypeChange(v: String) { _uiState.update { it.copy(fileType = v, error = null, success = null) } }

    fun onFileSelected(path: String) {
        val name = path.substringAfterLast("/").substringAfterLast("\\").takeLast(50)
        _uiState.update { it.copy(selectedFilePath = path, selectedFileName = name, error = null, success = null) }
    }

    fun submit() {
        val state = _uiState.value
        if (state.selectedFilePath.isEmpty()) { _uiState.update { it.copy(error = "Please select a file") }; return }
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null, success = null) }
            try {
                val fileBytes = java.io.File(UriUtil.getPath(app, state.selectedFilePath) ?: state.selectedFilePath).readBytes()
                documentRepository.saveDocument(
                    fileBytes = fileBytes,
                    fileName = state.selectedFileName.ifEmpty { "document" },
                    clientName = state.clientName.ifEmpty { "Unknown" },
                    caller = authDataStore.userKey ?: "",
                    mobileNo = state.mobileNo,
                    fileType = state.fileType.ifEmpty { "Other" },
                ).let { result ->
                    when (result) {
                        is com.crm.v2.common.result.Resource.Success -> {
                            _uiState.update { it.copy(isLoading = false, success = "Document uploaded successfully", selectedFilePath = "", selectedFileName = "") }
                        }
                        is com.crm.v2.common.result.Resource.Error -> {
                            _uiState.update { it.copy(isLoading = false, error = result.message) }
                        }
                        else -> {}
                    }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = "Failed to read file: ${e.message}") }
            }
        }
    }
}

object UriUtil {
    fun getPath(context: android.content.Context, uriString: String): String? {
        if (!uriString.startsWith("content://")) return uriString
        val cursor = context.contentResolver.query(android.net.Uri.parse(uriString), null, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val idx = it.getColumnIndex(android.provider.OpenableColumns.DISPLAY_NAME)
                if (idx >= 0) return it.getString(idx)
            }
        }
        return null
    }
}
