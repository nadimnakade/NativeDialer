package com.crm.v2.presentation.ui.reports

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crm.v2.domain.model.AttendanceData
import com.crm.v2.domain.model.LiveCount
import com.crm.v2.domain.model.PerformanceData
import com.crm.v2.presentation.ui.components.CrmLoading
import com.crm.v2.presentation.ui.components.StatCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportsScreen(
    viewModel: ReportsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val tabs = listOf(
        TabData("Performance", Icons.Filled.EmojiEvents),
        TabData("Attendance", Icons.Filled.CalendarMonth),
        TabData("Live Count", Icons.Filled.Equalizer),
    )
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) { viewModel.setActiveTab(0) }

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
                    containerColor = Color(0xFFFEE2E2),
                    contentColor = Color(0xFFDC2626),
                )
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF8FAFC)),
        ) {
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

            when (uiState.activeTab) {
                0 -> {
                    if (uiState.isLoadingPerformance) CrmLoading()
                    else PerformanceTab(data = uiState.performance)
                }
                1 -> {
                    if (uiState.isLoadingAttendance) CrmLoading()
                    else AttendanceTab(data = uiState.attendance)
                }
                2 -> {
                    if (uiState.isLoadingLiveCount) CrmLoading()
                    else LiveCountTab(
                        data = uiState.dialerLive,
                        isAutoRefreshing = uiState.isAutoRefreshing,
                        nextRefreshInSeconds = uiState.nextRefreshInSeconds,
                    )
                }
            }
        }
    }
}

@Composable
private fun PerformanceTab(data: List<PerformanceData>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        itemsIndexed(data) { _, item ->
            Card(
                modifier = Modifier.fillMaxWidth(),
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
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        item.key,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Text(
                        item.value.toString(),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            }
        }
    }
}

@Composable
private fun AttendanceTab(data: AttendanceData) {
    val stats = listOf(
        "Total Days" to data.totalDays.toString(),
        "Working Days" to data.workingDays.toString(),
        "Absent" to data.absentDays.toString(),
        "Half Day" to data.halfDay.toString(),
        "Late" to data.late.toString(),
        "Salary" to "\u20B9${data.salary.toInt()}",
        "Earned" to "\u20B9${data.salaryEarned.toInt()}",
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Attendance Summary",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(16.dp))

                stats.chunked(2).forEach { row ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        row.forEach { (label, value) ->
                            StatCard(
                                label = label,
                                value = value,
                                modifier = Modifier.weight(1f),
                            )
                        }
                        if (row.size < 2) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}

@Composable
private fun LiveCountTab(
    data: LiveCount,
    isAutoRefreshing: Boolean,
    nextRefreshInSeconds: Int,
) {
    val callStats = listOf(
        "Total" to data.totalCount.toString(),
        "Connected" to data.interested.toString(),
        "Follow Up" to data.followUp.toString(),
        "Not Int." to data.notInterested.toString(),
        "Ringing" to data.ringing.toString(),
        "DND" to data.dnd.toString(),
        "NC" to data.notContactable.toString(),
        "Wrong No" to data.wrongNo.toString(),
        "Not Doable" to data.notDoable.toString(),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        // Auto-refresh indicator
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFEFF6FF)),
        ) {
            Row(
                modifier = Modifier.padding(12.dp).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                if (isAutoRefreshing) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(16.dp),
                        strokeWidth = 2.dp,
                        color = Color(0xFF2563EB),
                    )
                } else {
                    Icon(
                        Icons.Filled.Refresh,
                        contentDescription = null,
                        tint = Color(0xFF2563EB),
                        modifier = Modifier.size(16.dp),
                    )
                }
                Text(
                    text = if (isAutoRefreshing) "Refreshing..." else "Auto-refresh in ${nextRefreshInSeconds}s",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF2563EB),
                    fontWeight = FontWeight.Medium,
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFDE3121).copy(alpha = 0.08f)),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Call Status Summary",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(14.dp))

                callStats.chunked(3).forEach { row ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        row.forEach { (label, value) ->
                            StatCard(
                                label = label,
                                value = value,
                                modifier = Modifier.weight(1f),
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Financial Summary",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(14.dp))

                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Total Login Amt", style = MaterialTheme.typography.bodyMedium)
                    Text(
                        "\u20B9${data.totalLoginAmt.toInt()}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFDE3121),
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider(color = Color(0xFFE2E8F0))
                Spacer(modifier = Modifier.height(10.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Disbursed", style = MaterialTheme.typography.bodyMedium)
                    Text(
                        "\u20B9${data.totalDisbursedAmt.toInt()}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFDE3121),
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider(color = Color(0xFFE2E8F0))
                Spacer(modifier = Modifier.height(10.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Duration", style = MaterialTheme.typography.bodyMedium)
                    Text(
                        data.duration,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFDE3121),
                    )
                }
            }
        }
    }
}

private data class TabData(val title: String, val icon: ImageVector)
