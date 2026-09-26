package com.crm.v2.presentation.ui.searchapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crm.v2.domain.model.SearchApplication
import com.crm.v2.presentation.ui.components.*

@Composable
fun SearchApplicationScreen(
    viewModel: SearchApplicationViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val listState = rememberLazyListState()

    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            viewModel.clearError()
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow {
            val lastVisible = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            val totalItems = listState.layoutInfo.totalItemsCount
            lastVisible >= totalItems - 3
        }.collect { shouldLoadMore ->
            if (shouldLoadMore && !uiState.isLoading && uiState.hasMore) {
                viewModel.loadMore()
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize().background(Color(0xFFF8FAFC))) {
        OutlinedTextField(
            value = uiState.searchQuery,
            onValueChange = { viewModel.onSearchQueryChange(it) },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            placeholder = { Text("Search applications...", color = Color(0xFF94A3B8)) },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search", tint = Color(0xFF94A3B8)) },
            trailingIcon = {
                if (uiState.searchQuery.isNotEmpty()) {
                    IconButton(onClick = { viewModel.onSearchQueryChange("") }) {
                        Icon(Icons.Filled.Clear, contentDescription = "Clear", tint = Color(0xFF94A3B8))
                    }
                }
            },
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

        if (uiState.error != null) {
            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFEE2E2)),
            ) {
                Text(
                    uiState.error!!,
                    modifier = Modifier.padding(12.dp),
                    color = Color(0xFFDC2626),
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }

        PullRefreshListWrapper(
            isRefreshing = uiState.isRefreshing,
            onRefresh = { viewModel.refresh() },
        ) {
            when {
                uiState.isLoading && uiState.applications.isEmpty() -> CrmLoading()
                uiState.applications.isEmpty() -> CrmEmptyState("No applications found")
                else -> LazyColumn(
                    state = listState,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(uiState.applications, key = { it.uniqId }) { app ->
                        ApplicationCard(app = app)
                    }

                    if (uiState.isLoading && uiState.applications.isNotEmpty()) {
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

@Composable
private fun ApplicationCard(app: SearchApplication) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = app.clientName.ifEmpty { "N/A" },
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        text = "App: ${app.appNo.ifEmpty { "N/A" }}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF94A3B8),
                    )
                }
                StatusBadge(status = app.statusDesc)
            }

            Spacer(modifier = Modifier.height(8.dp))

            HorizontalDivider(color = Color(0xFFE2E8F0))

            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                DetailColumn(label = "Product", value = app.loanName, modifier = Modifier.weight(1f))
                DetailColumn(label = "Bank", value = app.bankName, modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                DetailColumn(label = "Loan Amt", value = app.loanAmount, modifier = Modifier.weight(1f))
                DetailColumn(label = "Disb Amt", value = app.disbursedAmount, modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(6.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                DetailColumn(label = "App Date", value = app.appDate, modifier = Modifier.weight(1f))
                DetailColumn(label = "Disb Date", value = app.disbursalDate, modifier = Modifier.weight(1f))
            }

            if (app.remarks.isNotEmpty()) {
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Remarks: ${app.remarks}",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF64748B),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
private fun DetailColumn(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = Color(0xFF94A3B8),
        )
        Text(
            text = value.ifEmpty { "-" },
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF1E293B),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
