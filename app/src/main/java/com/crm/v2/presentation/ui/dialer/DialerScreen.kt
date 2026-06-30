package com.crm.v2.presentation.ui.dialer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crm.v2.domain.model.DialerRecord
import com.crm.v2.presentation.ui.components.StatusBadge
import com.crm.v2.telephony.CallState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialerScreen(
    onActiveCall: (String) -> Unit,
    onUpdateStatus: (String) -> Unit,
    viewModel: DialerViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val tabs = listOf("Auto", "Manual", "Contacts")

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

        when (uiState.activeTab) {
            0 -> AutoDialTab(
                isAutoDialing = uiState.isAutoDialing,
                currentLead = uiState.currentLead,
                queue = uiState.dialerQueue,
                onStart = viewModel::startAutoDial,
                onStop = viewModel::stopAutoDial,
            )
            1 -> ManualDialTab(
                phoneNumber = uiState.phoneNumber,
                error = uiState.error,
                isLoading = uiState.isLoading,
                callState = uiState.callState,
                lastCallMessage = uiState.lastCallMessage,
                onNumberChanged = viewModel::onPhoneNumberChanged,
                onCall = { viewModel.manualCall(uiState.phoneNumber) },
                onHangup = viewModel::hangup,
                onClearError = viewModel::clearError,
            )
            2 -> ContactTab()
        }
    }
}

@Composable
private fun AutoDialTab(
    isAutoDialing: Boolean,
    currentLead: DialerRecord?,
    queue: List<DialerRecord>,
    onStart: () -> Unit,
    onStop: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if (isAutoDialing) {
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = onStop,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.error,
                        ),
                    ) {
                        Icon(Icons.Filled.Stop, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Stop Dialing")
                    }
                } else {
                    Button(onClick = onStart) {
                        Icon(Icons.Filled.PlayArrow, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Start Dialing")
                    }
                }
            }
        }

        currentLead?.let { lead ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                ),
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Current Lead", style = MaterialTheme.typography.labelMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(lead.clientName, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text(lead.contact, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item { Text("Queue (${queue.size})", style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(vertical = 8.dp)) }
            items(queue, key = { it.key }) { lead ->
                DialerLeadCard(lead = lead)
            }
        }
    }
}

@Composable
private fun DialerLeadCard(lead: DialerRecord) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(lead.clientName, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                Text(lead.contact, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Column(horizontalAlignment = Alignment.End) {
                lead.status?.let { StatusBadge(status = it) }
                Text(lead.product ?: "", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

@Composable
private fun ManualDialTab(
    phoneNumber: String,
    error: String?,
    isLoading: Boolean,
    callState: CallState,
    lastCallMessage: String?,
    onNumberChanged: (String) -> Unit,
    onCall: () -> Unit,
    onHangup: () -> Unit,
    onClearError: () -> Unit,
) {
    val isActiveCall = callState is CallState.Calling || callState is CallState.Ringing ||
            callState is CallState.Connected || callState is CallState.OnHold

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { new ->
                onNumberChanged(new.filter { it.isDigit() }.take(10))
                onClearError()
            },
            label = { Text("Phone Number") },
            placeholder = { Text("Enter 10-digit number") },
            singleLine = true,
            enabled = !isActiveCall,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            leadingIcon = { Icon(Icons.Filled.Phone, contentDescription = null) },
            isError = error != null,
            supportingText = error?.let { { Text(it, color = MaterialTheme.colorScheme.error) } },
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Call state indicator
        AnimatedVisibility(
            visible = isActiveCall || isLoading,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = when (callState) {
                        is CallState.Connected -> MaterialTheme.colorScheme.primaryContainer
                        is CallState.OnHold -> MaterialTheme.colorScheme.tertiaryContainer
                        is CallState.Error -> MaterialTheme.colorScheme.errorContainer
                        else -> MaterialTheme.colorScheme.surfaceVariant
                    }
                ),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            strokeWidth = 2.dp,
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("Connecting...", style = MaterialTheme.typography.bodyMedium)
                    } else {
                        Icon(
                            imageVector = when (callState) {
                                is CallState.Connected -> Icons.Filled.PhoneInTalk
                                is CallState.OnHold -> Icons.Filled.Pause
                                is CallState.Calling -> Icons.Filled.Phone
                                is CallState.Ringing -> Icons.Filled.PhoneInTalk
                                else -> Icons.Filled.Phone
                            },
                            contentDescription = null,
                            tint = when (callState) {
                                is CallState.Connected -> MaterialTheme.colorScheme.primary
                                is CallState.OnHold -> MaterialTheme.colorScheme.tertiary
                                else -> MaterialTheme.colorScheme.onSurfaceVariant
                            },
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            callState.label,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium,
                        )
                    }
                }
            }
        }

        // Last call message
        lastCallMessage?.let { msg ->
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = msg,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Call / Hangup button
        if (isActiveCall) {
            FilledTonalButton(
                onClick = onHangup,
                modifier = Modifier.size(80.dp),
                shape = MaterialTheme.shapes.extraLarge,
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = Color.White,
                ),
            ) {
                Icon(
                    Icons.Filled.CallEnd,
                    contentDescription = "Hangup",
                    modifier = Modifier.size(32.dp),
                )
            }
        } else {
            FilledTonalButton(
                onClick = onCall,
                modifier = Modifier.size(80.dp),
                shape = MaterialTheme.shapes.extraLarge,
                enabled = phoneNumber.length == 10 && !isLoading,
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(32.dp),
                        strokeWidth = 3.dp,
                    )
                } else {
                    Icon(
                        Icons.Filled.PhoneInTalk,
                        contentDescription = "Call",
                        modifier = Modifier.size(32.dp),
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = if (phoneNumber.isEmpty()) "Enter number to call" else "${phoneNumber.length}/10 digits",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun ContactTab() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            "Contacts",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}
