package com.crm.v2.presentation.ui.documents

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crm.v2.domain.model.Document
import com.crm.v2.presentation.ui.components.CrmEmptyState
import com.crm.v2.presentation.ui.components.CrmLoading

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentListScreen(
    onUpload: () -> Unit,
    viewModel: DocumentViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        // Search section
        Card(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Search Client", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = uiState.searchQuery,
                        onValueChange = viewModel::onSearchQueryChanged,
                        placeholder = { Text("Enter mobile number") },
                        singleLine = true,
                        modifier = Modifier.weight(1f),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(onClick = viewModel::searchClients) {
                        Icon(Icons.Filled.Search, contentDescription = "Search")
                    }
                }
            }
        }

        when {
            uiState.selectedClient != null -> {
                // Show documents for selected client
                Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                    Row(
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(uiState.selectedClient!!.name ?: "", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                            Text(uiState.selectedClient!!.mobileNo ?: "", style = MaterialTheme.typography.bodySmall)
                        }
                        TextButton(onClick = viewModel::clearSelection) { Text("Back") }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                if (uiState.isLoading) CrmLoading()
                else if (uiState.documents.isEmpty()) CrmEmptyState("No documents for this client")
                else LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(uiState.documents) { doc -> DocumentCard(doc = doc) }
                }
            }
            else -> {
                // Show search results
                if (uiState.isSearching) CrmLoading()
                else if (uiState.clients.isEmpty()) CrmEmptyState("Search for a client to view documents")
                else LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(uiState.clients, key = { it.key }) { client ->
                        Card(
                            onClick = { viewModel.selectClient(client) },
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Row(modifier = Modifier.padding(16.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Filled.Person, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                                Spacer(modifier = Modifier.width(12.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(client.name ?: "", style = MaterialTheme.typography.titleSmall)
                                    Text(client.mobileNo ?: "", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                                Icon(Icons.Filled.ChevronRight, contentDescription = "View", tint = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DocumentCard(doc: Document) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = when (doc.fileType?.lowercase()) {
                    "pdf" -> Icons.Filled.PictureAsPdf
                    "doc", "docx" -> Icons.Filled.Description
                    "xls", "xlsx" -> Icons.Filled.TableChart
                    "jpg", "jpeg", "png" -> Icons.Filled.Image
                    else -> Icons.Filled.InsertDriveFile
                },
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp),
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(doc.fileName ?: "Document", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(doc.fileType ?: "", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Icon(Icons.Filled.Download, contentDescription = "Download", tint = MaterialTheme.colorScheme.primary)
        }
    }
}
