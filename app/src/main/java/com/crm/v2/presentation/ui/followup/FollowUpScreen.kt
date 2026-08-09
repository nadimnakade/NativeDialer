package com.crm.v2.presentation.ui.followup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crm.v2.common.util.maskPhone
import com.crm.v2.presentation.ui.components.CrmEmptyState
import com.crm.v2.presentation.ui.components.CrmLoading
import com.crm.v2.presentation.ui.components.StatusBadge

@OptIn(ExperimentalMaterial3Api::class)
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
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            snackbarHostState.showSnackbar(it, duration = SnackbarDuration.Short)
            viewModel.clearError()
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer,
                )
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background),
        ) {
            TopAppBar(
                title = {
                    Text(
                        "Follow-ups",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
            )

            ScrollableTabRow(
                selectedTabIndex = uiState.activeTab,
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary,
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
                                )
                                Text(
                                    tab.title,
                                    fontWeight = if (uiState.activeTab == index) FontWeight.SemiBold else FontWeight.Normal,
                                    style = MaterialTheme.typography.labelLarge,
                                )
                            }
                        },
                    )
                }
            }

            when {
                uiState.isLoading -> CrmLoading()
                uiState.records.isEmpty() -> CrmEmptyState("No ${tabs[uiState.activeTab].title.lowercase()} records")
                else -> LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    items(uiState.records, key = { it.key }) { record ->
                        FollowUpCard(
                            record = record,
                            showCallbackDate = uiState.activeTab == 0,
                            onCall = { onCall(record.contact) },
                        )
                    }
                }
            }
        }
    }
}

private data class TabData(val title: String, val icon: ImageVector)

@Composable
private fun FollowUpCard(
    record: com.crm.v2.domain.model.DialerRecord,
    showCallbackDate: Boolean,
    onCall: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onCall() },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.tertiaryContainer),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = record.clientName.take(1).uppercase(),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary,
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    record.clientName,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    record.contact.maskPhone(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                if (showCallbackDate) {
                    record.callbackDate?.let { date ->
                        if (date.isNotBlank()) {
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                "Callback: $date",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.primary,
                            )
                        }
                    }
                }
                record.remarks?.let { remarks ->
                    if (remarks.isNotBlank()) {
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            remarks,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                            maxLines = 1,
                            overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis,
                        )
                    }
                }
            }

            Column(horizontalAlignment = Alignment.End) {
                record.status?.let { StatusBadge(status = it) }
                Spacer(modifier = Modifier.height(6.dp))
                Icon(
                    Icons.Filled.PhoneInTalk,
                    contentDescription = "Call",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp),
                )
            }
        }
    }
}
