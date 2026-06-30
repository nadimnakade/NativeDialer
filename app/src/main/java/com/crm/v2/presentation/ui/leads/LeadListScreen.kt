package com.crm.v2.presentation.ui.leads

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PhoneInTalk
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crm.v2.presentation.ui.components.CrmLoading
import com.crm.v2.presentation.ui.components.CrmEmptyState
import com.crm.v2.presentation.ui.components.StatusBadge

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeadListScreen(
    onLeadClick: (String) -> Unit,
    onAddLead: () -> Unit,
    viewModel: LeadListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val listState = rememberLazyListState()

    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text("Search leads...") },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
            singleLine = true,
        )

        if (uiState.isLoading) {
            CrmLoading()
        } else if (uiState.leads.isEmpty()) {
            CrmEmptyState("No leads found")
        } else {
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f),
            ) {
                items(uiState.leads, key = { it.key }) { lead ->
                    LeadCard(
                        lead = lead,
                        onClick = { onLeadClick(lead.key) },
                    )
                }

                if (uiState.isLoadingMore) {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth().padding(16.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            CircularProgressIndicator(modifier = Modifier.size(24.dp))
                        }
                    }
                }
            }
        }
    }

    FloatingActionButton(
        onClick = onAddLead,
        modifier = Modifier
            .padding(16.dp),
        containerColor = MaterialTheme.colorScheme.primary,
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Add Lead", tint = MaterialTheme.colorScheme.onPrimary)
    }
}

@Composable
private fun LeadCard(
    lead: com.crm.v2.domain.model.Lead,
    onClick: () -> Unit,
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = lead.clientName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = lead.contact,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                if (!lead.product.isNullOrBlank()) {
                    Text(
                        text = lead.product,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
            Column(horizontalAlignment = Alignment.End) {
                lead.status?.let { StatusBadge(status = it) }
                Spacer(modifier = Modifier.height(4.dp))
                Icon(
                    Icons.Filled.PhoneInTalk,
                    contentDescription = "Call",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp),
                )
            }
        }
    }
}
