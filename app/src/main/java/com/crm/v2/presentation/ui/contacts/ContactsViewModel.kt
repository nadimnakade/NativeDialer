package com.crm.v2.presentation.ui.contacts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crm.v2.domain.model.Contact
import com.crm.v2.domain.repository.ContactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ContactsUiState(
    val contacts: List<Contact> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
)

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val contactRepository: ContactRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ContactsUiState())
    val uiState: StateFlow<ContactsUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            contactRepository.getCachedContacts().collect { contacts ->
                _uiState.update { it.copy(contacts = contacts, isLoading = false) }
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        viewModelScope.launch {
            if (query.length >= 2) {
                contactRepository.searchContacts(query).collect { contacts ->
                    _uiState.update { it.copy(contacts = contacts) }
                }
            } else {
                contactRepository.getCachedContacts().collect { contacts ->
                    _uiState.update { it.copy(contacts = contacts) }
                }
            }
        }
    }
}
