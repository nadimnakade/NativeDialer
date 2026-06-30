package com.crm.v2.presentation.ui.contacts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crm.v2.domain.model.Contact
import com.crm.v2.presentation.ui.components.CrmLoading
import com.crm.v2.presentation.ui.components.CrmEmptyState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactsScreen(
    onCall: (String) -> Unit,
    viewModel: ContactsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = uiState.searchQuery,
            onValueChange = viewModel::onSearchQueryChanged,
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            placeholder = { Text("Search contacts...") },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
            singleLine = true,
        )

        if (uiState.isLoading) {
            CrmLoading()
        } else if (uiState.contacts.isEmpty()) {
            CrmEmptyState("No contacts found")
        } else {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                items(uiState.contacts, key = { it.id ?: it.name ?: "" }) { contact ->
                    ContactCard(contact = contact, onCall = { contact.phoneNumbers.firstOrNull()?.let(onCall) })
                }
            }
        }
    }
}

@Composable
private fun ContactCard(contact: Contact, onCall: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Row(
            modifier = Modifier.padding(12.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                Icons.Filled.Person,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(contact.name ?: "Unknown", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                contact.phoneNumbers.firstOrNull()?.let { number ->
                    Text(number, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
            IconButton(onClick = onCall) {
                Icon(Icons.Filled.Call, contentDescription = "Call", tint = MaterialTheme.colorScheme.primary)
            }
        }
    }
}
