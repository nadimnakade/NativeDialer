package com.crm.v2.presentation.ui.dialer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.automirrored.filled.Backspace
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crm.v2.domain.model.Contact
import com.crm.v2.domain.model.DialerRecord
import com.crm.v2.telephony.CallState

@Composable
fun DialerScreen(
    onNavigateBack: () -> Unit,
    viewModel: DialerViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.showUpdateStatus && uiState.currentLead != null -> {
            UpdateStatusScreen(
                leadKey = uiState.currentLead!!.key,
                clientName = uiState.currentLead!!.clientName,
                mobileNo = uiState.currentLead!!.contact,
                callDuration = uiState.callDuration,
                onSave = { state ->
                    viewModel.submitStatus(
                        key = uiState.currentLead!!.key,
                        status = state.selectedStatus?.value ?: "",
                        callbackDate = if (state.callbackDate.isNotEmpty()) "${state.callbackDate} ${state.callbackTime}" else null,
                        remarks = state.remarks,
                        userKey = viewModel.getUserKey(),
                    )
                },
                onCancel = { viewModel.dismissUpdateStatus() },
            )
        }

        uiState.callState.isActive || uiState.callState is CallState.Calling || uiState.callState is CallState.Ringing -> {
            ActiveCallScreen(
                number = uiState.currentLead?.contact ?: "",
                clientName = uiState.currentLead?.clientName ?: "",
                callState = uiState.callState,
                onHangup = { viewModel.hangup() },
                onMute = { viewModel.toggleMute() },
                onSpeaker = { viewModel.toggleSpeaker() },
                onHold = { viewModel.toggleHold() },
                onNavigateToUpdateStatus = { viewModel.hangup() },
            )
        }

        else -> {
            DialerMainContent(
                uiState = uiState,
                viewModel = viewModel,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DialerMainContent(
    uiState: DialerUiState,
    viewModel: DialerViewModel,
) {
    val tabs = listOf("Auto", "Lead", "Contact")

    LaunchedEffect(Unit) {
        viewModel.loadDialerQueue()
    }

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
                uiState = uiState,
                onStart = viewModel::startAutoDial,
                onStop = viewModel::stopAutoDial,
                onCallLead = { lead -> viewModel.callLead(lead) },
                onRefresh = viewModel::refreshDialerQueue,
                onSkip = { viewModel.skipCurrentLead() },
            )
            1 -> LeadTab(
                uiState = uiState,
                onCallLead = { lead -> viewModel.callLead(lead) },
                onRefresh = viewModel::refreshLeads,
                onLoadMore = { viewModel.loadLeads() },
            )
            2 -> ContactTab(
                uiState = uiState,
                onSearchChanged = viewModel::onContactSearchChanged,
                onCallContact = { contact -> viewModel.callContact(contact) },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AutoDialTab(
    uiState: DialerUiState,
    onStart: () -> Unit,
    onStop: () -> Unit,
    onCallLead: (DialerRecord) -> Unit,
    onRefresh: () -> Unit,
    onSkip: () -> Unit,
) {
    PullToRefreshBox(
        isRefreshing = uiState.isRefreshing,
        onRefresh = onRefresh,
        modifier = Modifier.fillMaxSize(),
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                    ),
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            "Auto Dialer",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            "Queue: ${uiState.dialerQueue.size} clients",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        if (uiState.isAutoDialing) {
                            uiState.currentLead?.let { lead ->
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.surface,
                                    ),
                                ) {
                                    Column(
                                        modifier = Modifier.padding(12.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                    ) {
                                        Text(
                                            when (uiState.callState) {
                                                is CallState.Calling -> "Dialing..."
                                                is CallState.Ringing -> "Ringing..."
                                                is CallState.Connected -> "Connected"
                                                is CallState.OnHold -> "On Hold"
                                                else -> "Calling..."
                                            },
                                            style = MaterialTheme.typography.labelMedium,
                                            color = MaterialTheme.colorScheme.primary,
                                        )
                                        Text(
                                            lead.clientName,
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold,
                                        )
                                        Text(
                                            lead.contact,
                                            style = MaterialTheme.typography.bodyMedium,
                                        )
                                        lead.company?.let {
                                            Text(
                                                it,
                                                style = MaterialTheme.typography.bodySmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                                        ) {
                                            FilledTonalButton(
                                                onClick = { onCallLead(lead) },
                                                modifier = Modifier.weight(1f),
                                            ) {
                                                Icon(Icons.Filled.Phone, contentDescription = null, modifier = Modifier.size(16.dp))
                                                Spacer(modifier = Modifier.width(4.dp))
                                                Text("Call", style = MaterialTheme.typography.labelMedium)
                                            }
                                            OutlinedButton(
                                                onClick = onSkip,
                                                modifier = Modifier.weight(1f),
                                            ) {
                                                Icon(Icons.Filled.SkipNext, contentDescription = null, modifier = Modifier.size(16.dp))
                                                Spacer(modifier = Modifier.width(4.dp))
                                                Text("Skip", style = MaterialTheme.typography.labelMedium)
                                            }
                                        }
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))
                            Button(
                                onClick = onStop,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.error,
                                ),
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Icon(Icons.Filled.Stop, contentDescription = null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Stop Dialing")
                            }
                        } else {
                            Button(
                                onClick = onStart,
                                modifier = Modifier.fillMaxWidth(),
                                enabled = uiState.dialerQueue.isNotEmpty(),
                            ) {
                                Icon(Icons.Filled.PlayArrow, contentDescription = null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Start Dialing")
                            }
                        }
                    }
                }
            }

            items(uiState.dialerQueue.size) { index ->
                val lead = uiState.dialerQueue[index]
                val isCurrent = uiState.currentLead?.key == lead.key
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = if (isCurrent) CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                    ) else CardDefaults.cardColors(),
                ) {
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .clickable { onCallLead(lead) },
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            Icons.Filled.Phone,
                            contentDescription = null,
                            tint = if (isCurrent) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(20.dp),
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                lead.clientName,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                            Text(
                                lead.contact,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                lead.company?.let {
                                    Text(
                                        it,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                }
                                lead.campaign?.let {
                                    Text(
                                        "• $it",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                    )
                                }
                            }
                        }
                        lead.statusLabel.let {
                            if (it != "Pending") {
                                Surface(
                                    shape = RoundedCornerShape(4.dp),
                                    color = MaterialTheme.colorScheme.secondaryContainer,
                                ) {
                                    Text(
                                        it,
                                        style = MaterialTheme.typography.labelSmall,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LeadTab(
    uiState: DialerUiState,
    onCallLead: (DialerRecord) -> Unit,
    onRefresh: () -> Unit,
    onLoadMore: () -> Unit,
) {
    PullToRefreshBox(
        isRefreshing = uiState.isRefreshing,
        onRefresh = onRefresh,
        modifier = Modifier.fillMaxSize(),
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                Text(
                    "Leads (${uiState.leadList.size})",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(bottom = 4.dp),
                )
            }

            items(uiState.leadList.size) { index ->
                val lead = uiState.leadList[index]
                Card(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .clickable { onCallLead(lead) },
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            Icons.Filled.Person,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp),
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                lead.clientName,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                            Text(
                                lead.contact,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                            lead.company?.let {
                                Text(
                                    it,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            }
                            lead.callbackDate?.let {
                                Text(
                                    "Follow-up: $it",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.tertiary,
                                )
                            }
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            lead.statusLabel.let {
                                if (it != "Pending") {
                                    Surface(
                                        shape = RoundedCornerShape(4.dp),
                                        color = MaterialTheme.colorScheme.secondaryContainer,
                                    ) {
                                        Text(
                                            it,
                                            style = MaterialTheme.typography.labelSmall,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            FilledTonalIconButton(
                                onClick = { onCallLead(lead) },
                                modifier = Modifier.size(36.dp),
                            ) {
                                Icon(Icons.Filled.Phone, contentDescription = "Call", modifier = Modifier.size(18.dp))
                            }
                        }
                    }
                }
            }

            if (uiState.hasMoreLeads && uiState.leadList.isNotEmpty()) {
                item {
                    Button(
                        onClick = onLoadMore,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !uiState.isLoading,
                    ) {
                        if (uiState.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                strokeWidth = 2.dp,
                                color = MaterialTheme.colorScheme.onPrimary,
                            )
                        } else {
                            Text("Load More")
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ContactTab(
    uiState: DialerUiState,
    onSearchChanged: (String) -> Unit,
    onCallContact: (Contact) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = uiState.contactSearchQuery,
            onValueChange = onSearchChanged,
            placeholder = { Text("Search contacts...") },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
            trailingIcon = {
                if (uiState.contactSearchQuery.isNotEmpty()) {
                    IconButton(onClick = { onSearchChanged("") }) {
                        Icon(Icons.Filled.Clear, contentDescription = "Clear")
                    }
                }
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
        )

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            val sortedKeys = uiState.groupedContacts.keys.sortedBy { it }

            sortedKeys.forEach { letter ->
                item(key = "header_$letter") {
                    Text(
                        text = letter.toString(),
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
                            .padding(horizontal = 12.dp, vertical = 4.dp),
                    )
                }

                val contacts = uiState.groupedContacts[letter] ?: emptyList()
                items(contacts.size) { index ->
                    val contact = contacts[index]
                    ContactItem(
                        contact = contact,
                        onClick = { onCallContact(contact) },
                    )
                }
            }
        }
    }
}

@Composable
private fun ContactItem(
    contact: Contact,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = contact.name?.firstOrNull()?.uppercase() ?: "?",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = contact.name ?: "Unknown",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            contact.phoneNumbers.firstOrNull()?.let { phone ->
                Text(
                    text = phone,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                )
            }
        }
        Icon(
            Icons.Filled.Phone,
            contentDescription = "Call",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f))
                .padding(8.dp),
        )
    }
}
