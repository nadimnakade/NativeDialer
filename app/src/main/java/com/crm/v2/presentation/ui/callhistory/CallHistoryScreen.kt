package com.crm.v2.presentation.ui.callhistory

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crm.v2.domain.model.CallLog
import com.crm.v2.presentation.ui.components.*

@Composable
fun CallHistoryScreen(
    onCall: (String) -> Unit,
    viewModel: CallHistoryViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize().background(Color(0xFFF8FAFC))) {
        uiState.error?.let { error ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
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
                uiState.isLoading && uiState.callLogs.isEmpty() -> CrmLoading()
                uiState.callLogs.isEmpty() -> CrmEmptyState("No call history")
                else -> LazyColumn {
                    items(uiState.callLogs, key = { it.key ?: it.callDate ?: "" }) { log ->
                        ClientListItem(
                            name = log.name ?: log.number ?: "Unknown",
                            phone = log.number ?: "",
                            subtitle = "${formatDuration(log.duration)} - ${log.type ?: ""}",
                            time = log.callDate,
                            onCall = { log.number?.let(onCall) },
                        )
                    }
                }
            }
        }
    }
}

private fun formatDuration(seconds: Int): String {
    val min = seconds / 60
    val sec = seconds % 60
    return "${min}m ${sec}s"
}
