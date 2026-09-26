package com.crm.v2.presentation.ui.contacts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crm.v2.common.util.maskPhone
import com.crm.v2.presentation.ui.components.*

@Composable
fun ContactsScreen(
    onCall: (String) -> Unit,
    viewModel: ContactsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize().background(Color(0xFFF8FAFC))) {
        OutlinedTextField(
            value = uiState.searchQuery,
            onValueChange = viewModel::onSearchQueryChanged,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            placeholder = { Text("Search contacts...", color = Color(0xFF94A3B8)) },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search", tint = Color(0xFF94A3B8)) },
            singleLine = true,
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFE2E8F0),
                focusedBorderColor = Color(0xFFDE3121),
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                cursorColor = Color(0xFFDE3121),
            ),
        )

        uiState.error?.let { error ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFEE2E2)),
            ) {
                Text(error, modifier = Modifier.padding(12.dp), color = Color(0xFFDC2626), style = MaterialTheme.typography.bodySmall)
            }
        }

        PullRefreshListWrapper(
            isRefreshing = uiState.isLoading,
            onRefresh = { },
        ) {
            when {
                uiState.contacts.isEmpty() -> CrmEmptyState("No contacts found")
                else -> LazyColumn {
                    items(uiState.contacts, key = { it.id ?: it.name ?: "" }) { contact ->
                        ClientListItem(
                            name = contact.name ?: "Unknown",
                            phone = contact.phoneNumbers.firstOrNull()?.maskPhone() ?: "",
                            onCall = { contact.phoneNumbers.firstOrNull()?.let(onCall) },
                        )
                    }
                }
            }
        }
    }
}
