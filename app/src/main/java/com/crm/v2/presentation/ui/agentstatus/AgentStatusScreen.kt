package com.crm.v2.presentation.ui.agentstatus

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crm.v2.presentation.ui.components.CrmEmptyState
import com.crm.v2.presentation.ui.components.CrmLoading

@Composable
fun AgentStatusScreen(
    viewModel: AgentStatusViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) { viewModel.load() }

    when {
        uiState.isLoading -> CrmLoading()
        uiState.agents.isEmpty() -> CrmEmptyState("No agent status data")
        else -> LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(uiState.agents, key = { it.key ?: "" }) { agent ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(agent.name ?: "Unknown", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                        Spacer(Modifier.height(4.dp))
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Type: ${agent.type ?: "-"}", style = MaterialTheme.typography.bodySmall)
                            Text("Duration: ${formatDuration(agent.duration)}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        Text(agent.number ?: "", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        }
    }
}

private fun formatDuration(seconds: Int): String {
    val h = seconds / 3600
    val m = (seconds % 3600) / 60
    val s = seconds % 60
    return "%02d:%02d:%02d".format(h, m, s)
}
