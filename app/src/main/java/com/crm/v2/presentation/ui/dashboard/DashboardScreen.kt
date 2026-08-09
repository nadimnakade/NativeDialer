package com.crm.v2.presentation.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    modifier = Modifier.size(32.dp),
                    color = MaterialTheme.colorScheme.onBackground,
                    strokeWidth = 2.dp,
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(top = 24.dp, bottom = 100.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
            ) {
                // Greeting
                item {
                    Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                        Text(
                            text = "Hello, ${uiState.employeeName.ifEmpty { "User" }}",
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground,
                            letterSpacing = (-1).sp,
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Here's your overview",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }

                // Ranking card
                item {
                    if (uiState.dashboardData.rank > 0) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.onBackground,
                            ),
                            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(52.dp)
                                        .clip(CircleShape)
                                        .background(Color(0xFFFCD34D)),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Icon(
                                        Icons.Filled.EmojiEvents,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.onBackground,
                                        modifier = Modifier.size(28.dp),
                                    )
                                }
                                Spacer(modifier = Modifier.width(16.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        "You are ranked #${uiState.dashboardData.rank}",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color.White,
                                    )
                                    Text(
                                        "with ${String.format("%.1f", uiState.dashboardData.points)} points",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color.White.copy(alpha = 0.7f),
                                    )
                                }
                            }
                        }
                    }
                }

                // Quick actions — 2-column bento
                item {
                    Column(
                        modifier = Modifier.padding(horizontal = 24.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            BentoActionCard(
                                icon = Icons.Filled.Call,
                                label = "Dialer",
                                subtitle = "Start calling",
                                onClick = onNavigateToDialer,
                                modifier = Modifier.weight(1f),
                                accentColor = MaterialTheme.colorScheme.secondary,
                            )
                            BentoActionCard(
                                icon = Icons.Filled.PersonAdd,
                                label = "Add Lead",
                                subtitle = "New contact",
                                onClick = onNavigateToLeads,
                                modifier = Modifier.weight(1f),
                                accentColor = MaterialTheme.colorScheme.tertiary,
                            )
                        }
                        BentoActionCard(
                            icon = Icons.Filled.Schedule,
                            label = "Follow-ups",
                            subtitle = "View pending callbacks",
                            onClick = onNavigateToFollowUps,
                            modifier = Modifier.fillMaxWidth(),
                            accentColor = Color(0xFF3B82F6),
                        )
                    }
                }

                // Stats — bento grid
                item {
                    StatsBentoSection(data = uiState.dashboardData)
                }

                // Live count
                item {
                    LiveCountBentoSection(liveCount = uiState.liveCount)
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
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer,
                    shape = MaterialTheme.shapes.small,
                )
            },
        )
    }
}

@Composable
private fun BentoActionCard(
    icon: ImageVector,
    label: String,
    subtitle: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    accentColor: Color = MaterialTheme.colorScheme.secondary,
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(accentColor.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = accentColor,
                    modifier = Modifier.size(22.dp),
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
private fun StatsBentoSection(data: DashboardData) {
    Column(
        modifier = Modifier.padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            "Performance",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground,
        )

        // First row — 2 big stats
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            BigStatCard(
                label = "Disbursed",
                value = "\u20B9${formatAmount(data.totalDisbursedAmt)}",
                modifier = Modifier.weight(1f),
                accentColor = MaterialTheme.colorScheme.secondary,
            )
            BigStatCard(
                label = "Rank",
                value = data.rank.toString(),
                modifier = Modifier.weight(1f),
                accentColor = MaterialTheme.colorScheme.tertiary,
            )
        }

        // Second row — 3 small stats
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            SmallStatCard(
                label = "Points",
                value = data.points.toString(),
                modifier = Modifier.weight(1f),
            )
            SmallStatCard(
                label = "FTD",
                value = data.clientConnectedFtd.toString(),
                modifier = Modifier.weight(1f),
            )
            SmallStatCard(
                label = "Leads",
                value = data.dailyLeads.toString(),
                modifier = Modifier.weight(1f),
            )
        }

        // Third row — 2 medium stats
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            MediumStatCard(
                label = "Duration",
                value = data.duration,
                modifier = Modifier.weight(1f),
            )
            MediumStatCard(
                label = "Connectivity",
                value = "${data.connectivityPercent.toInt()}%",
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Composable
private fun BigStatCard(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    accentColor: Color = MaterialTheme.colorScheme.onBackground,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = accentColor,
                letterSpacing = (-0.5).sp,
            )
        }
    }
}

@Composable
private fun SmallStatCard(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                letterSpacing = (-0.3).sp,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
private fun MediumStatCard(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = value,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        }
    }
}

@Composable
private fun LiveCountBentoSection(liveCount: LiveCount) {
    Column(
        modifier = Modifier.padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            "Call Status",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onBackground,
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    LiveStatItem("Connected", liveCount.interested.toString(), Color(0xFF5EEAD4))
                    LiveStatItem("Follow Up", liveCount.followUp.toString(), Color(0xFF93C5FD))
                    LiveStatItem("Not Int.", liveCount.notInterested.toString(), Color(0xFFFCA5A5))
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    LiveStatItem("Ringing", liveCount.ringing.toString(), Color(0xFFFCD34D))
                    LiveStatItem("DND", liveCount.dnd.toString(), Color(0xFFFCA5A5))
                    LiveStatItem("Total", liveCount.totalCount.toString(), Color.White)
                }
            }
        }
    }
}

@Composable
private fun LiveStatItem(label: String, value: String, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = color,
            letterSpacing = (-0.3).sp,
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = Color.White.copy(alpha = 0.6f),
        )
    }
}

private fun formatAmount(amount: Double): String {
    return if (amount >= 10000000) "${(amount / 10000000).toInt()}Cr"
    else if (amount >= 100000) "${(amount / 100000).toInt()}L"
    else amount.toInt().toString()
}
