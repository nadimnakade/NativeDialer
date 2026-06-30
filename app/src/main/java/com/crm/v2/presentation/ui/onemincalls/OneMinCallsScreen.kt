package com.crm.v2.presentation.ui.onemincalls

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crm.v2.presentation.ui.components.CrmEmptyState
import com.crm.v2.presentation.ui.components.CrmLoading

@Composable
fun OneMinCallsScreen(
    viewModel: OneMinCallsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) { viewModel.load() }
    when {
        uiState.isLoading -> CrmLoading()
        uiState.calls.isEmpty() -> CrmEmptyState("No calls over 1 minute")
        else -> LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(uiState.calls, key = { it.key ?: "" }) { call ->
                Card(Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(16.dp)) {
                        Text(call.name ?: "Unknown", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Spacer(Modifier.height(4.dp))
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(call.number ?: "", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text(formatDuration(call.duration), style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}

private fun formatDuration(seconds: Int): String {
    val m = seconds / 60
    val s = seconds % 60
    return "${m}m ${s}s"
}
