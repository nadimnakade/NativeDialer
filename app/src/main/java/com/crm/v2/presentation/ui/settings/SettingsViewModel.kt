package com.crm.v2.presentation.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crm.v2.common.result.Resource
import com.crm.v2.data.datastore.AuthDataStore
import com.crm.v2.data.datastore.DarkModePreference
import com.crm.v2.data.datastore.SettingsDataStore
import com.crm.v2.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SettingsUiState(
    val darkMode: DarkModePreference = DarkModePreference.SYSTEM,
    val autoDialInterval: Int = 10,
    val callRecordingEnabled: Boolean = true,
    val employeeName: String = "",
    val appVersion: String = "2.0.0",
)

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsDataStore: SettingsDataStore,
    private val authDataStore: AuthDataStore,
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            settingsDataStore.darkMode.collect { mode ->
                _uiState.update { it.copy(darkMode = mode) }
            }
        }
        viewModelScope.launch {
            settingsDataStore.autoDialInterval.collect { interval ->
                _uiState.update { it.copy(autoDialInterval = interval) }
            }
        }
        viewModelScope.launch {
            settingsDataStore.callRecordingEnabled.collect { enabled ->
                _uiState.update { it.copy(callRecordingEnabled = enabled) }
            }
        }
        _uiState.update { it.copy(employeeName = authDataStore.employeeName ?: "") }
    }

    fun setDarkMode(mode: DarkModePreference) {
        viewModelScope.launch { settingsDataStore.setDarkMode(mode) }
    }

    fun setAutoDialInterval(seconds: Int) {
        viewModelScope.launch { settingsDataStore.setAutoDialInterval(seconds) }
    }

    fun setCallRecordingEnabled(enabled: Boolean) {
        viewModelScope.launch { settingsDataStore.setCallRecordingEnabled(enabled) }
    }

    fun logout() {
        viewModelScope.launch { authRepository.logout() }
    }
}
