package com.crm.v2.presentation.ui.followup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crm.v2.common.util.maskPhone
import com.crm.v2.presentation.ui.components.*

@Composable
fun FollowUpScreen(
    onCall: (String) -> Unit,
    viewModel: FollowUpViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val tabs = listOf(
        TabData("Follow Up", Icons.Filled.FollowTheSigns),
        TabData("Same Day", Icons.Filled.Today),
        TabData("+1 Min", Icons.Filled.Timer),
        TabData("Ringing", Icons.Filled.RingVolume),
    )

    LaunchedEffect(Unit) { viewModel.loadRecords() }

    Column(modifier = Modifier.fillMaxSize().background(Color(0xFFF8FAFC))) {
        ScrollableTabRow(
            selectedTabIndex = uiState.activeTab,
            containerColor = Color.White,
            contentColor = Color(0xFFDE3121),
            divider = {},
            edgePadding = 16.dp,
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = uiState.activeTab == index,
                    onClick = { viewModel.setActiveTab(index) },
                    text = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                        ) {
                            Icon(
                                tab.icon,
                                contentDescription = tab.title,
                                modifier = Modifier.size(18.dp),
                                tint = if (uiState.activeTab == index) Color(0xFFDE3121) else Color(0xFF94A3B8),
                            )
                            Text(
                                tab.title,
                                fontWeight = if (uiState.activeTab == index) FontWeight.SemiBold else FontWeight.Normal,
                                style = MaterialTheme.typography.labelLarge,
                                color = if (uiState.activeTab == index) Color(0xFFDE3121) else Color(0xFF64748B),
                            )
                        }
                    },
                )
            }
        }

        PullRefreshListWrapper(
            isRefreshing = uiState.isLoading,
            onRefresh = { viewModel.loadRecords() },
        ) {
            when {
                uiState.isLoading && uiState.records.isEmpty() -> CrmLoading()
                uiState.records.isEmpty() -> CrmEmptyState("No ${tabs[uiState.activeTab].title.lowercase()} records")
                else -> LazyColumn {
                    items(uiState.records, key = { it.key }) { record ->
                        ClientListItem(
                            name = record.clientName,
                            phone = record.contact.maskPhone(),
                            subtitle = record.remarks,
                            onCall = { onCall(record.contact) },
                        )
                    }
                }
            }
        }
    }
}

private data class TabData(val title: String, val icon: ImageVector)
