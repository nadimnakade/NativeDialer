package com.crm.v2.presentation.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crm.v2.common.result.Resource
import com.crm.v2.domain.model.User
import com.crm.v2.domain.usecase.auth.LoginUseCase
import com.crm.v2.domain.usecase.auth.LogoutUseCase
import com.crm.v2.presentation.common.Event
import com.crm.v2.presentation.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

data class LoginUiState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isPasswordVisible: Boolean = false,
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val logoutUseCase: LogoutUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _events = Channel<Event>(Channel.BUFFERED)
    val events: Flow<Event> = _events.receiveAsFlow()

    fun onUsernameChanged(username: String) {
        _uiState.update { it.copy(username = username, error = null) }
    }

    fun onPasswordChanged(password: String) {
        _uiState.update { it.copy(password = password, error = null) }
    }

    fun togglePasswordVisibility() {
        _uiState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }

    fun login() {
        val state = _uiState.value
        if (state.username.isBlank()) {
            _uiState.update { it.copy(error = "Username is required") }
            return
        }
        if (state.password.isBlank()) {
            _uiState.update { it.copy(error = "Password is required") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            val uuid = UUID.randomUUID().toString()
            when (val result = loginUseCase(state.username, state.password, uuid)) {
                is Resource.Success -> {
                    _uiState.update { it.copy(isLoading = false) }
                    _events.send(Event.NavigateAndClear("dashboard"))
                }
                is Resource.Error -> {
                    _uiState.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
                is Resource.Loading -> { }
            }
        }
    }
}
