package com.crm.v2.presentation.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crm.v2.common.constants.ApiConstants
import com.crm.v2.data.config.ExtensionConfigProvider
import com.crm.v2.domain.model.DashboardData
import com.crm.v2.presentation.ui.components.*

private val Red1 = Color(0xFFDC2626)
private val Red2 = Color(0xFFB91C1C)
private val Red3 = Color(0xFF991B1B)
private val Maroon = Color(0xFF7F1D1D)
private val MaroonDark = Color(0xFF450A0A)

@Composable
fun DashboardScreen(
    onNavigateToDialer: () -> Unit,
    onNavigateToLeads: () -> Unit,
    onNavigateToFollowUps: () -> Unit,
    onNavigateToReports: () -> Unit,
    viewModel: DashboardViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = androidx.compose.ui.platform.LocalContext.current
    val extStatus by viewModel.extensionConfigProvider.extensionStatus.collectAsState()

    uiState.updateInfo?.let { updateInfo ->
        UpdateDialog(
            updateInfo = updateInfo,
            onUpdate = { viewModel.downloadUpdate(context) },
            onDismiss = { viewModel.dismissUpdate() },
        )
    }

    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            snackbarHostState.showSnackbar(message = it, duration = SnackbarDuration.Short)
            viewModel.clearError()
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFFF8FAFC))) {
        if (uiState.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(modifier = Modifier.size(32.dp), color = Red1, strokeWidth = 2.dp)
            }
        } else {
            PullToRefreshBox(
                isRefreshing = uiState.isRefreshing,
                onRefresh = { viewModel.refreshDashboard() },
                modifier = Modifier.fillMaxSize(),
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(top = 8.dp, bottom = 24.dp),
                ) {
                    item {
                        GreetingBanner(
                            employeeName = uiState.employeeName.ifEmpty { "User" },
                            modifier = Modifier.padding(horizontal = 20.dp),
                        )
                    }

                    item(key = "sip_info_${extStatus.isLoaded}") {
                        SipInfoCard(
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
                        )
                    }

                    item {
                        QuickActionCards(
                            onDialer = onNavigateToDialer,
                            onAddLead = onNavigateToLeads,
                            onFollowUps = onNavigateToFollowUps,
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 16.dp),
                        )
                    }

                    item {
                        PerformanceOverview(
                            data = uiState.dashboardData,
                            modifier = Modifier.padding(horizontal = 20.dp),
                        )
                    }

                    item {
                        LeaderboardSection(
                            data = uiState.dashboardData,
                            modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
                        )
                    }
                }
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            snackbar = { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = Color.White,
                    shape = RoundedCornerShape(12.dp),
                )
            },
        )
    }
}

@Composable
private fun GreetingBanner(employeeName: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(Red1, Red2, Red3),
                    start = Offset(0f, 0f),
                    end = Offset(1000f, 500f),
                )
            )
            .padding(20.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Hello, $employeeName \uD83D\uDC4B",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    letterSpacing = (-0.5).sp,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Make every call count!",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.85f),
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White.copy(alpha = 0.2f))
                    .border(1.dp, Color.White.copy(alpha = 0.3f), RoundedCornerShape(16.dp))
                    .padding(horizontal = 14.dp, vertical = 10.dp),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Text(
                            text = "You're doing",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White.copy(alpha = 0.85f),
                        )
                        Text(
                            text = "Great!",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Filled.BarChart,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(28.dp),
                    )
                }
            }
        }
    }
}

@Composable
private fun QuickActionCards(
    onDialer: () -> Unit,
    onAddLead: () -> Unit,
    onFollowUps: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        QuickActionItem(
            icon = Icons.Filled.Call,
            label = "Dialer",
            subtitle = "Start calling",
            bgColor = Color(0xFFFEE2E2),
            iconColor = Red1,
            onClick = onDialer,
            modifier = Modifier.weight(1f),
        )
        QuickActionItem(
            icon = Icons.Filled.PersonAdd,
            label = "Add Lead",
            subtitle = "New contact",
            bgColor = Color(0xFFEDE9FE),
            iconColor = Color(0xFF7C3AED),
            onClick = onAddLead,
            modifier = Modifier.weight(1f),
        )
        QuickActionItem(
            icon = Icons.Filled.Schedule,
            label = "Follow-ups",
            subtitle = "View callbacks",
            bgColor = Color(0xFFFFF7ED),
            iconColor = Color(0xFFEA580C),
            onClick = onFollowUps,
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun QuickActionItem(
    icon: ImageVector,
    label: String,
    subtitle: String,
    bgColor: Color,
    iconColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(14.dp),
    ) {
        Column {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(bgColor),
                contentAlignment = Alignment.Center,
            ) {
                Icon(imageVector = icon, contentDescription = label, tint = iconColor, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E293B),
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF94A3B8),
            )
        }
    }
}

@Composable
private fun PerformanceOverview(data: DashboardData, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                "Performance Overview",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E293B),
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            StatCardLarge(
                label = "Disbursed",
                value = "\u20B9${formatIndianAmount(data.totalDisbursedAmt)}",
                icon = {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFFEF2F2)),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(Icons.Filled.CurrencyRupee, null, tint = Red1, modifier = Modifier.size(18.dp))
                    }
                },
                modifier = Modifier.weight(1f),
            )
            StatCardLarge(
                label = "Rank",
                value = "#${data.rank}",
                icon = {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFFEF3C7)),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(Icons.Filled.EmojiEvents, null, tint = Color(0xFFD97706), modifier = Modifier.size(18.dp))
                    }
                },
                modifier = Modifier.weight(1f),
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            StatCardSmall(label = "Points", value = String.format("%.1f", data.points), icon = Icons.Filled.EmojiEvents, iconColor = Color(0xFFD97706), modifier = Modifier.weight(1f))
            StatCardSmall(label = "FTD", value = data.clientConnectedFtd.toString(), icon = Icons.Filled.Groups, iconColor = Color(0xFF2563EB), modifier = Modifier.weight(1f))
            StatCardSmall(label = "Leads", value = data.dailyLeads.toString(), icon = Icons.Filled.Assignment, iconColor = Color(0xFF7C3AED), modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            StatCardSmall(label = "Duration", value = data.duration, icon = Icons.Filled.Timer, iconColor = Red1, modifier = Modifier.weight(1f))
            StatCardSmall(label = "Connectivity", value = "${data.connectivityPercent.toInt()}%", icon = Icons.Filled.SignalCellularAlt, iconColor = Color(0xFFEA580C), modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun StatCardLarge(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    icon: @Composable (() -> Unit)? = null,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp))
            .padding(16.dp),
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(label, style = MaterialTheme.typography.bodySmall, color = Color(0xFF64748B), fontWeight = FontWeight.Medium)
                icon?.invoke()
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E293B),
                letterSpacing = (-0.5).sp,
            )
        }
    }
}

@Composable
private fun StatCardSmall(
    label: String,
    value: String,
    icon: ImageVector,
    iconColor: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp))
            .padding(14.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                Text(
                    text = value,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E293B),
                )
                Text(label, style = MaterialTheme.typography.labelSmall, color = Color(0xFF94A3B8))
            }
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(iconColor.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(icon, null, tint = iconColor, modifier = Modifier.size(18.dp))
            }
        }
    }
}

@Composable
private fun LeaderboardSection(data: DashboardData, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(Red2, Maroon, MaroonDark),
                    start = Offset(0f, 0f),
                    end = Offset(1200f, 800f),
                )
            )
            .padding(20.dp),
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("\uD83C\uDFC6", fontSize = 20.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Leaderboard", color = Color.White, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White.copy(alpha = 0.1f))
                    .padding(20.dp),
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Your Rank", color = Color.White.copy(alpha = 0.7f), style = MaterialTheme.typography.labelMedium)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "#${data.rank}",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 48.sp,
                    )
                }
            }
        }
    }
}

private fun formatIndianAmount(amount: Double): String {
    if (amount == 0.0) return "0"
    val formatter = java.text.NumberFormat.getCurrencyInstance(java.util.Locale("en", "IN"))
    return formatter.format(amount).removeSuffix(".00").removePrefix("\u20B9")
}

@Composable
private fun SipInfoCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEFF6FF)),
        shape = RoundedCornerShape(12.dp),
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                "SIP Config",
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                color = Color(0xFF1E40AF),
            )
            Spacer(modifier = Modifier.height(6.dp))
            SipInfoRow("Extension", ApiConstants.Sip.USERNAME)
            SipInfoRow("SIP Server", "${ApiConstants.Sip.SERVER}:${ApiConstants.Sip.PORT}")
            SipInfoRow("REST API", ApiConstants.CloudConnect.BASE_URL)
            SipInfoRow("Token", ApiConstants.CloudConnect.TOKEN_ID)
        }
    }
}

@Composable
private fun SipInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.padding(vertical = 1.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            "$label: ",
            fontSize = 11.sp,
            color = Color(0xFF64748B),
        )
        Text(
            value,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF1E293B),
        )
    }
}
