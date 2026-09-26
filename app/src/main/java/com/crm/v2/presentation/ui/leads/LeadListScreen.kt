package com.crm.v2.presentation.ui.leads

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PhoneInTalk
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crm.v2.common.util.maskPhone
import com.crm.v2.presentation.ui.components.*

@Composable
fun LeadListScreen(
    onLeadClick: (String) -> Unit,
    onAddLead: () -> Unit,
    viewModel: LeadListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) { viewModel.loadLeads() }

    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            snackbarHostState.showSnackbar(it, duration = SnackbarDuration.Short)
            viewModel.clearError()
        }
    }

    Scaffold(
        snackbarHost = { ErrorSnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddLead,
                containerColor = Color(0xFFDE3121),
                shape = CircleShape,
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Lead", tint = Color.White)
            }
        },
    ) { padding ->
        PullRefreshListWrapper(
            isRefreshing = uiState.isLoading,
            onRefresh = { viewModel.loadLeads() },
            modifier = Modifier.padding(padding),
        ) {
            Column(modifier = Modifier.fillMaxSize().background(Color(0xFFF8FAFC))) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    placeholder = { Text("Search leads...", color = Color(0xFF94A3B8)) },
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

                when {
                    uiState.isLoading && uiState.leads.isEmpty() -> CrmLoading()
                    uiState.leads.isEmpty() -> CrmEmptyState("No leads found")
                    else -> LazyColumn {
                        items(uiState.leads, key = { it.key }) { lead ->
                            ClientListItem(
                                name = lead.clientName,
                                phone = lead.contact.maskPhone(),
                                subtitle = lead.product,
                                onCall = { },
                                onClick = { onLeadClick(lead.key) },
                            )
                        }

                        if (uiState.isLoadingMore) {
                            item {
                                Box(
                                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(24.dp),
                                        color = Color(0xFFDE3121),
                                        strokeWidth = 2.dp,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
