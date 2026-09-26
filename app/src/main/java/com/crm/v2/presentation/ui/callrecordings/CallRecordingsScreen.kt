package com.crm.v2.presentation.ui.callrecordings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crm.v2.presentation.ui.components.*

@Composable
fun CallRecordingsScreen(
    viewModel: CallRecordingsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) { viewModel.load() }

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
            onRefresh = { viewModel.load() },
        ) {
            when {
                uiState.isLoading && uiState.recordings.isEmpty() -> CrmLoading()
                uiState.recordings.isEmpty() -> CrmEmptyState("No call recordings available")
                else -> LazyColumn {
                    items(uiState.recordings, key = { it.key ?: "" }) { rec ->
                        ClientListItem(
                            name = rec.clientName ?: "Unknown",
                            phone = rec.fileName ?: "",
                            onCall = { },
                        )
                    }
                }
            }
        }
    }
}
