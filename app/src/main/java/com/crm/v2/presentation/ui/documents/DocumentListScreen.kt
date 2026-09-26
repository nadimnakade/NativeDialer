package com.crm.v2.presentation.ui.documents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crm.v2.domain.model.Document
import com.crm.v2.presentation.ui.components.*

@Composable
fun DocumentListScreen(
    onUpload: () -> Unit,
    viewModel: DocumentViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize().background(Color(0xFFF8FAFC))) {
        Card(modifier = Modifier.fillMaxWidth().padding(16.dp), shape = RoundedCornerShape(14.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Search Client", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = uiState.searchQuery,
                        onValueChange = viewModel::onSearchQueryChanged,
                        placeholder = { Text("Enter mobile number", color = Color(0xFF94A3B8)) },
                        singleLine = true,
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(14.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color(0xFFE2E8F0),
                            focusedBorderColor = Color(0xFFDE3121),
                            unfocusedContainerColor = Color(0xFFF8FAFC),
                            focusedContainerColor = Color(0xFFF8FAFC),
                            cursorColor = Color(0xFFDE3121),
                        ),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    IconButton(onClick = viewModel::searchClients) {
                        Icon(Icons.Filled.Search, contentDescription = "Search", tint = Color(0xFFDE3121))
                    }
                }
            }
        }

        uiState.error?.let { error ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFEE2E2)),
            ) {
                Text(error, modifier = Modifier.padding(12.dp), color = Color(0xFFDC2626), style = MaterialTheme.typography.bodySmall)
            }
        }

        when {
            uiState.selectedClient != null -> {
                Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), shape = RoundedCornerShape(14.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
                    Row(
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(uiState.selectedClient!!.name ?: "", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                            Text(uiState.selectedClient!!.mobileNo ?: "", style = MaterialTheme.typography.bodySmall, color = Color(0xFF94A3B8))
                        }
                        TextButton(onClick = viewModel::clearSelection) { Text("Back", color = Color(0xFFDE3121)) }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                PullRefreshListWrapper(
                    isRefreshing = uiState.isLoading,
                    onRefresh = { },
                ) {
                    when {
                        uiState.documents.isEmpty() -> CrmEmptyState("No documents for this client")
                        else -> LazyColumn {
                            items(uiState.documents) { doc ->
                                DocumentCard(doc = doc)
                            }
                        }
                    }
                }
            }
            else -> {
                PullRefreshListWrapper(
                    isRefreshing = uiState.isSearching,
                    onRefresh = { },
                ) {
                    when {
                        uiState.clients.isEmpty() && !uiState.isSearching -> CrmEmptyState("Search for a client to view documents")
                        else -> LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp)) {
                            items(uiState.clients, key = { it.key }) { client ->
                                ClientListItem(
                                    name = client.name ?: "Unknown",
                                    phone = client.mobileNo ?: "",
                                    onCall = { viewModel.selectClient(client) },
                                )
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
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    ) {
        Row(
            modifier = Modifier.padding(14.dp).fillMaxWidth(),
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
                tint = Color(0xFFDE3121),
                modifier = Modifier.size(32.dp),
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(doc.fileName ?: "Document", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(doc.fileType ?: "", style = MaterialTheme.typography.labelSmall, color = Color(0xFF94A3B8))
            }
            Icon(Icons.Filled.Download, contentDescription = "Download", tint = Color(0xFFDE3121))
        }
    }
}
