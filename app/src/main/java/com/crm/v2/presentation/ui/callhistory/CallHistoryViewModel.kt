package com.crm.v2.presentation.ui.callhistory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crm.v2.data.database.dao.CallLogDao
import com.crm.v2.domain.model.CallLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CallHistoryUiState(
    val callLogs: List<CallLog> = emptyList(),
    val isLoading: Boolean = false,
)

@HiltViewModel
class CallHistoryViewModel @Inject constructor(
    private val callLogDao: CallLogDao,
) : ViewModel() {

    private val _uiState = MutableStateFlow(CallHistoryUiState())
    val uiState: StateFlow<CallHistoryUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            callLogDao.getCallLogs("", 200).collect { entities ->
                _uiState.update { it.copy(
                    callLogs = entities.map { e -> CallLog(key = e.key, name = e.name, number = e.number, type = e.type, duration = e.duration, callDate = e.callDate) },
                    isLoading = false,
                )}
            }
        }
    }
}
