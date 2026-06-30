package com.crm.v2.presentation.common

sealed interface UiState<out T> {
    data object Idle : UiState<Nothing>
    data object Loading : UiState<Nothing>
    data class Success<T>(val data: T) : UiState<T>
    data class Error(val message: String) : UiState<Nothing>
}

sealed interface Event {
    data object NavigateBack : Event
    data class ShowSnackbar(val message: String) : Event
    data class NavigateTo(val route: String) : Event
    data class NavigateAndClear(val route: String) : Event
}
