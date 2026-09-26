package com.crm.v2.presentation.ui.agentstatus

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crm.v2.presentation.ui.components.*

@Composable
fun AgentStatusScreen(
    viewModel: AgentStatusViewModel = hiltViewModel(),
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
                uiState.isLoading && uiState.agents.isEmpty() -> CrmLoading()
                uiState.agents.isEmpty() -> CrmEmptyState("No agent status data")
                else -> LazyColumn {
                    items(uiState.agents, key = { it.key ?: "" }) { agent ->
                        ClientListItem(
                            name = agent.name ?: "Unknown",
                            phone = agent.number ?: "",
                            subtitle = "${agent.type ?: "-"} - ${formatDuration(agent.duration)}",
                            onCall = {},
                        )
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
