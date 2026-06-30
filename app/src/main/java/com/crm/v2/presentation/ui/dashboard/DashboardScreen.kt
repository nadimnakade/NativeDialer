package com.crm.v2.presentation.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crm.v2.domain.model.DashboardData
import com.crm.v2.domain.model.LiveCount
import com.crm.v2.presentation.ui.components.*
import com.crm.v2.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
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

    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            snackbarHostState.showSnackbar(
                message = it,
                duration = SnackbarDuration.Short,
            )
            viewModel.clearError()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (uiState.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(48.dp),
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(Dimens.space16),
                verticalArrangement = Arrangement.spacedBy(Dimens.space20),
            ) {
                item { WelcomeSection() }
                item { QuickActionsRow(onNavigateToDialer, onNavigateToLeads, onNavigateToFollowUps) }
                item { StatsGridSection(data = uiState.dashboardData) }
                item { LiveCountSection(liveCount = uiState.liveCount) }
                item { DisbursalBreakdownSection(data = uiState.dashboardData) }
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
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer,
                    shape = MaterialTheme.shapes.medium,
                )
            },
        )
    }
}

@Composable
private fun WelcomeSection() {
    Column {
        Text(
            text = "Dashboard",
            style = MaterialTheme.typography.headlineLarge,
        )
        Spacer(modifier = Modifier.height(Dimens.space6))
        Text(
            text = "Here's your performance overview",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun QuickActionsRow(
    onDialer: () -> Unit,
    onLeads: () -> Unit,
    onFollowUps: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Dimens.space12),
    ) {
        QuickActionCard(
            icon = Icons.Filled.Call,
            label = "Dialer",
            onClick = onDialer,
            modifier = Modifier.weight(1f),
        )
        QuickActionCard(
            icon = Icons.Filled.PersonAdd,
            label = "Add Lead",
            onClick = onLeads,
            modifier = Modifier.weight(1f),
        )
        QuickActionCard(
            icon = Icons.Filled.Schedule,
            label = "Follow-ups",
            onClick = onFollowUps,
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun QuickActionCard(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        onClick = onClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = Dimens.elevationNone),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.space16),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.size(Dimens.iconXl),
            )
            Spacer(modifier = Modifier.height(Dimens.space8))
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        }
    }
}

@Composable
private fun StatsGridSection(data: DashboardData) {
    Column {
        CrmSectionHeader("Performance Stats")
        Spacer(modifier = Modifier.height(Dimens.space12))

        val stats = listOf(
            StatItem(Icons.Filled.EmojiEvents, "Rank", data.rank.toString(), "Your current ranking"),
            StatItem(Icons.Filled.Stars, "Points", data.points.toString(), "Performance points earned"),
            StatItem(Icons.Filled.AccountBalanceWallet, "Disbursed", "₹${formatAmount(data.totalDisbursedAmt)}", "Total amount disbursed"),
            StatItem(Icons.Filled.TrendingUp, "FTD", data.clientConnectedFtd.toString(), "First time disbursements"),
            StatItem(Icons.Filled.Timer, "Duration", data.duration, "Total call duration"),
            StatItem(Icons.Filled.Leaderboard, "Daily Leads", data.dailyLeads.toString(), "Leads generated today"),
            StatItem(Icons.Filled.Payments, "Login Amt", "₹${formatAmount(data.loginAmount)}", "Monthly login amount"),
            StatItem(Icons.Filled.Assignment, "Logins", data.loginCount.toString(), "Total logins this month"),
            StatItem(Icons.Filled.SignalCellularAlt, "Connectivity", "${data.connectivityPercent.toInt()}%", "Network connectivity"),
        )

        stats.forEach { stat ->
            StatCardRow(stat)
            Spacer(modifier = Modifier.height(Dimens.space8))
        }
    }
}

private data class StatItem(
    val icon: ImageVector,
    val title: String,
    val value: String,
    val subtitle: String,
)

@Composable
private fun StatCardRow(stat: StatItem) {
    OutlinedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = CardDefaults.outlinedCardBorder(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.space16, vertical = Dimens.space14),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = stat.icon,
                    contentDescription = stat.title,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(22.dp),
                )
            }

            Spacer(modifier = Modifier.width(Dimens.space14))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stat.title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Text(
                    text = stat.value,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }

            Text(
                text = stat.subtitle,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
private fun LiveCountSection(liveCount: LiveCount) {
    CrmCard {
        Column(modifier = Modifier.padding(Dimens.space16)) {
            CrmSectionHeader("Call Status Breakdown")
            Spacer(modifier = Modifier.height(Dimens.space12))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                StatusPill("Connected", liveCount.interested.toString(), MaterialTheme.colorScheme.tertiary)
                StatusPill("Follow Up", liveCount.followUp.toString(), MaterialTheme.colorScheme.primary)
                StatusPill("Not Int.", liveCount.notInterested.toString(), MaterialTheme.colorScheme.error)
            }
            Spacer(modifier = Modifier.height(Dimens.space16))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                StatusPill("Ringing", liveCount.ringing.toString(), MaterialTheme.colorScheme.error)
                StatusPill("DND", liveCount.dnd.toString(), MaterialTheme.colorScheme.error)
                StatusPill("Total", liveCount.totalCount.toString(), MaterialTheme.colorScheme.secondary)
            }
        }
    }
}

@Composable
private fun StatusPill(label: String, value: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            color = color,
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun DisbursalBreakdownSection(data: DashboardData) {
    CrmCard {
        Column(modifier = Modifier.padding(Dimens.space16)) {
            CrmSectionHeader("Disbursal Breakdown")
            Spacer(modifier = Modifier.height(Dimens.space10))
            Text(
                text = "Total Disbursed: ₹${formatAmount(data.totalDisbursedAmt)}",
                style = MaterialTheme.typography.titleSmall,
            )
        }
    }
}

private fun formatAmount(amount: Double): String {
    return if (amount >= 10000000) "${(amount / 10000000).toInt()}Cr"
    else if (amount >= 100000) "${(amount / 100000).toInt()}L"
    else amount.toInt().toString()
}
