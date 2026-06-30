package com.crm.v2.presentation.ui.reports

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crm.v2.domain.model.AttendanceData
import com.crm.v2.domain.model.LiveCount
import com.crm.v2.domain.model.PerformanceData
import com.crm.v2.presentation.ui.components.CrmLoading
import com.crm.v2.presentation.ui.components.StatCard

@Composable
fun ReportsScreen(
    viewModel: ReportsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val tabs = listOf("Performance", "Attendance", "Live Count")

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(selectedTabIndex = uiState.activeTab) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = uiState.activeTab == index,
                    onClick = { viewModel.setActiveTab(index) },
                    text = { Text(title) },
                )
            }
        }

        if (uiState.isLoading) {
            CrmLoading()
        } else {
            when (uiState.activeTab) {
                0 -> PerformanceTab(data = uiState.performance)
                1 -> AttendanceTab(data = uiState.attendance)
                2 -> LiveCountTab(data = uiState.dialerLive)
            }
        }
    }
}

@Composable
private fun PerformanceTab(data: List<PerformanceData>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        itemsIndexed(data) { _, item ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(item.key, style = MaterialTheme.typography.bodyLarge)
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
        "Salary" to "₹${data.salary.toInt()}",
        "Earned" to "₹${data.salaryEarned.toInt()}",
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Attendance Summary",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(16.dp))

                // 2-column grid using Rows (no fixed height)
                stats.chunked(2).forEach { row ->
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
                        // Fill empty space if odd number of items
                        if (row.size < 2) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
private fun LiveCountTab(data: LiveCount) {
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
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
            ),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Call Status Summary",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(12.dp))

                // 3-column grid using Rows (no fixed height)
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
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Financial Summary",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Total Login Amt", style = MaterialTheme.typography.bodyMedium)
                    Text(
                        "₹${data.totalLoginAmt.toInt()}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Total Disbursed", style = MaterialTheme.typography.bodyMedium)
                    Text(
                        "₹${data.totalDisbursedAmt.toInt()}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Duration", style = MaterialTheme.typography.bodyMedium)
                    Text(
                        data.duration,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}
