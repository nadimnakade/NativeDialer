package com.crm.v2.presentation.ui.callhistory

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crm.v2.domain.model.CallLog
import com.crm.v2.presentation.ui.components.CrmEmptyState
import com.crm.v2.presentation.ui.components.CrmLoading

@Composable
fun CallHistoryScreen(
    onCall: (String) -> Unit,
    viewModel: CallHistoryViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.isLoading -> CrmLoading()
        uiState.callLogs.isEmpty() -> CrmEmptyState("No call history")
        else -> LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            items(uiState.callLogs, key = { it.key ?: it.callDate ?: "" }) { log ->
                CallLogCard(log = log, onCall = { log.number?.let(onCall) })
            }
        }
    }
}

@Composable
private fun CallLogCard(log: CallLog, onCall: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(12.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = when (log.type?.uppercase()) {
                    "INCOMING" -> Icons.Filled.ArrowDownward
                    "OUTGOING" -> Icons.Filled.ArrowUpward
                    "MISSED" -> Icons.Filled.PhoneMissed
                    "REJECTED" -> Icons.Filled.PhoneDisabled
                    else -> Icons.Filled.Phone
                },
                contentDescription = log.type,
                tint = when (log.type?.uppercase()) {
                    "INCOMING" -> MaterialTheme.colorScheme.primary
                    "OUTGOING" -> MaterialTheme.colorScheme.tertiary
                    "MISSED" -> MaterialTheme.colorScheme.error
                    "REJECTED" -> MaterialTheme.colorScheme.error
                    else -> MaterialTheme.colorScheme.onSurfaceVariant
                },
                modifier = Modifier.size(28.dp),
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(log.name ?: log.number ?: "Unknown", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(log.number ?: "", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(formatDuration(log.duration), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Text(log.callDate ?: "", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            IconButton(onClick = onCall) {
                Icon(Icons.Filled.PhoneInTalk, contentDescription = "Call", tint = MaterialTheme.colorScheme.primary)
            }
        }
    }
}

private fun formatDuration(seconds: Int): String {
    val min = seconds / 60
    val sec = seconds % 60
    return "${min}m ${sec}s"
}
