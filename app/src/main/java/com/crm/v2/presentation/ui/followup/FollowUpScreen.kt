package com.crm.v2.presentation.ui.followup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhoneInTalk
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crm.v2.presentation.ui.components.CrmEmptyState
import com.crm.v2.presentation.ui.components.CrmLoading
import com.crm.v2.presentation.ui.components.StatusBadge

@Composable
fun FollowUpScreen(
    onCall: (String) -> Unit,
    viewModel: FollowUpViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val tabs = listOf("Follow Up", "Same Day", "+1 Min", "Ringing")

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(selectedTabIndex = uiState.activeTab) {
            tabs.forEachIndexed { index, title ->
                Tab(selected = uiState.activeTab == index, onClick = { viewModel.setActiveTab(index) }, text = { Text(title, maxLines = 1) })
            }
        }

        when {
            uiState.isLoading -> CrmLoading()
            uiState.records.isEmpty() -> CrmEmptyState("No ${tabs[uiState.activeTab].lowercase()} records")
            else -> LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(uiState.records, key = { it.key }) { record ->
                    Card(
                        modifier = Modifier.fillMaxWidth().clickable { onCall(record.contact) },
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp).fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(record.clientName, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                                Text(record.contact, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                record.callbackDate?.let { date ->
                                    if (date.isNotBlank()) {
                                        Text("Callback: $date", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                                    }
                                }
                            }
                            Column(horizontalAlignment = Alignment.End) {
                                record.status?.let { StatusBadge(status = it) }
                                Spacer(modifier = Modifier.height(4.dp))
                                Icon(Icons.Filled.PhoneInTalk, contentDescription = "Call", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}
