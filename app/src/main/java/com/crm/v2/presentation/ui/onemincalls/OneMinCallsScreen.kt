package com.crm.v2.presentation.ui.onemincalls

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crm.v2.common.util.maskPhone
import com.crm.v2.presentation.ui.components.*

@Composable
fun OneMinCallsScreen(
    viewModel: OneMinCallsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) { viewModel.load() }

    Column(modifier = Modifier.fillMaxSize().background(Color(0xFFF8FAFC))) {
        uiState.error?.let { error ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFEE2E2)),
            ) {
                Text(error, modifier = Modifier.padding(12.dp), color = Color(0xFFDC2626), style = MaterialTheme.typography.bodySmall)
            }
        }

        PullRefreshListWrapper(
            isRefreshing = uiState.isLoading,
            onRefresh = { viewModel.load() },
        ) {
            when {
                uiState.isLoading && uiState.calls.isEmpty() -> CrmLoading()
                uiState.calls.isEmpty() -> CrmEmptyState("No calls over 1 minute")
                else -> LazyColumn {
                    items(uiState.calls, key = { it.key ?: "" }) { call ->
                        ClientListItem(
                            name = call.name ?: "Unknown",
                            phone = (call.number ?: "").maskPhone(),
                            subtitle = "${call.duration / 60}m ${call.duration % 60}s",
                            onCall = {},
                        )
                    }
                }
            }
        }
    }
}
