package com.crm.v2.presentation.ui.dialer

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Backspace
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crm.v2.domain.model.Contact
import com.crm.v2.domain.model.DialerRecord
import com.crm.v2.presentation.ui.common.LoadingOverlay
import com.crm.v2.telephony.CallState

@Composable
fun DialerScreen(
    onNavigateBack: () -> Unit,
    viewModel: DialerViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            snackbarHostState.showSnackbar(it, duration = SnackbarDuration.Short)
            viewModel.clearError()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                LoadingOverlay(
                    isVisible = uiState.isLoading || uiState.isSaving,
                    message = if (uiState.isSaving) "Saving status..." else "Please wait...",
                )

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
                                    callDuration = uiState.callDuration,
                                    isHLLAPBL = state.isHLLAPBL,
                                    isCibilBad = state.isCibilBad,
                                    isRealEstate = state.isRealEstate,
                                )
                            },
                            onCancel = { viewModel.dismissUpdateStatus() },
                        )
                    }

                    uiState.callState.isActive -> {
                        ActiveCallScreen(
                            number = uiState.currentLead?.contact ?: "",
                            clientName = uiState.currentLead?.clientName ?: "",
                            callState = uiState.callState,
                            isMuted = uiState.isMuted,
                            isSpeakerOn = uiState.isSpeakerOn,
                            isOnHold = uiState.isOnHold,
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
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DialerMainContent(
    uiState: DialerUiState,
    viewModel: DialerViewModel,
) {
    val tabs = listOf("Auto", "Lead", "Contact", "Manual")

    LaunchedEffect(Unit) {
        viewModel.loadDialerQueue()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Dialer", fontWeight = FontWeight.Bold) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
        )

        TabRow(
            selectedTabIndex = uiState.activeTab,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary,
            divider = {},
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = uiState.activeTab == index,
                    onClick = { viewModel.setActiveTab(index) },
                    text = {
                        Text(
                            title,
                            fontWeight = if (uiState.activeTab == index) FontWeight.Bold else FontWeight.Normal,
                        )
                    },
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
            3 -> ManualDialTab(
                phoneNumber = uiState.phoneNumber,
                onNumberChanged = viewModel::onPhoneNumberChanged,
                onClear = viewModel::clearManualNumber,
                onCall = { number -> viewModel.callManualNumber(number) },
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
) {
    PullToRefreshBox(
        isRefreshing = uiState.isRefreshing,
        onRefresh = onRefresh,
        modifier = Modifier.fillMaxSize(),
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            Icon(
                                Icons.Filled.Dialpad,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.size(24.dp),
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                "Auto Dialer",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            "${uiState.dialerQueue.size} clients in queue",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f),
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        if (uiState.isAutoDialing) {
                            uiState.currentLead?.let { lead ->
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.surface,
                                    ),
                                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                                ) {
                                    Column(
                                        modifier = Modifier.padding(16.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                    ) {
                                        Surface(
                                            shape = MaterialTheme.shapes.small,
                                            color = when (uiState.callState) {
                                                is CallState.Calling -> Color(0xFF6366F1).copy(alpha = 0.1f)
                                                is CallState.Ringing -> Color(0xFF10B981).copy(alpha = 0.1f)
                                                is CallState.Connected -> Color(0xFF10B981).copy(alpha = 0.1f)
                                                is CallState.OnHold -> Color(0xFFF59E0B).copy(alpha = 0.1f)
                                                else -> MaterialTheme.colorScheme.surfaceVariant
                                            },
                                        ) {
                                            Text(
                                                when (uiState.callState) {
                                                    is CallState.Calling -> "Dialing..."
                                                    is CallState.Ringing -> "Ringing..."
                                                    is CallState.Connected -> "Connected"
                                                    is CallState.OnHold -> "On Hold"
                                                    else -> "Calling..."
                                                },
                                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                                style = MaterialTheme.typography.labelSmall,
                                                fontWeight = FontWeight.SemiBold,
                                                color = when (uiState.callState) {
                                                    is CallState.Calling -> Color(0xFF6366F1)
                                                    is CallState.Ringing, is CallState.Connected -> Color(0xFF10B981)
                                                    is CallState.OnHold -> Color(0xFFF59E0B)
                                                    else -> MaterialTheme.colorScheme.onSurfaceVariant
                                                },
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text(
                                            lead.clientName,
                                            style = MaterialTheme.typography.titleMedium,
                                            fontWeight = FontWeight.Bold,
                                        )
                                        Text(
                                            lead.contact,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        )
                                        lead.company?.let {
                                            Text(
                                                it,
                                                style = MaterialTheme.typography.bodySmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(12.dp))
                                        FilledTonalButton(
                                            onClick = { onCallLead(lead) },
                                            modifier = Modifier.fillMaxWidth(),
                                            shape = RoundedCornerShape(10.dp),
                                        ) {
                                            Icon(Icons.Filled.Phone, contentDescription = null, modifier = Modifier.size(18.dp))
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text("Call Now")
                                        }
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))
                            Button(
                                onClick = onStop,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.error,
                                ),
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(10.dp),
                            ) {
                                Icon(Icons.Filled.Stop, contentDescription = null, modifier = Modifier.size(20.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Stop Dialing", fontWeight = FontWeight.SemiBold)
                            }
                        } else {
                            Button(
                                onClick = onStart,
                                modifier = Modifier.fillMaxWidth(),
                                enabled = uiState.dialerQueue.isNotEmpty(),
                                shape = RoundedCornerShape(10.dp),
                            ) {
                                Icon(Icons.Filled.PlayArrow, contentDescription = null, modifier = Modifier.size(20.dp))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Start Dialing", fontWeight = FontWeight.SemiBold)
                            }
                        }
                    }
                }
            }

            if (uiState.dialerQueue.isNotEmpty()) {
                item {
                    Text(
                        "Queue",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 4.dp),
                    )
                }
            }

            items(uiState.dialerQueue.size) { index ->
                val lead = uiState.dialerQueue[index]
                val isCurrent = uiState.currentLead?.key == lead.key && uiState.isAutoDialing
                QueueLeadCard(
                    lead = lead,
                    isCurrent = isCurrent,
                    onClick = { onCallLead(lead) },
                )
            }

            if (uiState.dialerQueue.isEmpty() && !uiState.isLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 48.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                Icons.Filled.PhoneDisabled,
                                contentDescription = null,
                                modifier = Modifier.size(48.dp),
                                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                "No clients in queue",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun QueueLeadCard(
    lead: DialerRecord,
    isCurrent: Boolean,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        colors = if (isCurrent) CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
        ) else CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isCurrent) 2.dp else 0.5.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(
                        if (isCurrent) MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
                        else MaterialTheme.colorScheme.surfaceVariant
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    lead.clientName.firstOrNull()?.uppercase() ?: "?",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (isCurrent) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    lead.clientName,
                    style = MaterialTheme.typography.bodyLarge,
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
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                    lead.campaign?.let {
                        Text(
                            "• $it",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }
            if (isCurrent) {
                Surface(
                    shape = MaterialTheme.shapes.extraSmall,
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                ) {
                    Text(
                        "Active",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                        color = MaterialTheme.colorScheme.primary,
                    )
                }
            } else {
                Icon(
                    Icons.Filled.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                    modifier = Modifier.size(20.dp),
                )
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        "Follow-up Leads",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        "${uiState.leadList.size}",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
            }

            items(uiState.leadList.size) { index ->
                val lead = uiState.leadList[index]
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onCallLead(lead) },
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp),
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.tertiaryContainer),
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(
                                Icons.Filled.Person,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.tertiary,
                                modifier = Modifier.size(20.dp),
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                lead.clientName,
                                style = MaterialTheme.typography.bodyLarge,
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
                                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                )
                            }
                            lead.callbackDate?.let {
                                Text(
                                    "Follow-up: $it",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.tertiary,
                                    fontWeight = FontWeight.Medium,
                                )
                            }
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            lead.statusLabel.let {
                                if (it != "Pending") {
                                    Surface(
                                        shape = MaterialTheme.shapes.extraSmall,
                                        color = MaterialTheme.colorScheme.secondaryContainer,
                                    ) {
                                        Text(
                                            it,
                                            style = MaterialTheme.typography.labelSmall,
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(6.dp))
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
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedButton(
                        onClick = onLoadMore,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !uiState.isLoading,
                        shape = RoundedCornerShape(10.dp),
                    ) {
                        if (uiState.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(18.dp),
                                strokeWidth = 2.dp,
                            )
                        } else {
                            Text("Load More")
                        }
                    }
                }
            }

            if (uiState.leadList.isEmpty() && !uiState.isLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 48.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                Icons.Filled.Inbox,
                                contentDescription = null,
                                modifier = Modifier.size(48.dp),
                                tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f),
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                "No follow-up leads",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                            )
                        }
                    }
                }
            }
        }
    }
}

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
            shape = RoundedCornerShape(12.dp),
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

            if (sortedKeys.isEmpty() && uiState.contactSearchQuery.isNotEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 48.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            "No contacts found",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                        )
                    }
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
            .padding(vertical = 10.dp, horizontal = 4.dp),
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
                fontWeight = FontWeight.Bold,
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

@Composable
private fun ManualDialTab(
    phoneNumber: String,
    onNumberChanged: (String) -> Unit,
    onClear: () -> Unit,
    onCall: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = phoneNumber,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = if (phoneNumber.isEmpty()) "Enter a number to dial" else "",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(modifier = Modifier.height(4.dp))

            val keys = listOf(
                listOf("1", "2", "3"),
                listOf("4", "5", "6"),
                listOf("7", "8", "9"),
                listOf("*", "0", "#"),
            )

            keys.forEach { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    row.forEach { key ->
                        DialKey(
                            key = key,
                            onPress = { onNumberChanged(phoneNumber + key) },
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                DialKey(
                    key = "",
                    isBackspace = true,
                    onPress = { onNumberChanged(phoneNumber.dropLast(1)) },
                )
            }
        }

        Column(
            modifier = Modifier.align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                onClick = { onCall(phoneNumber) },
                enabled = phoneNumber.trim().length >= 6,
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF10B981),
                    contentColor = Color.White,
                ),
            ) {
                Icon(
                    Icons.Filled.Phone,
                    contentDescription = "Call",
                    modifier = Modifier.size(24.dp),
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DialKey(
    key: String,
    onPress: () -> Unit,
    isBackspace: Boolean = false,
    modifier: Modifier = Modifier.size(56.dp),
) {
    Column(
        modifier = modifier
            .padding(4.dp)
            .clip(CircleShape)
            .clickable(onClick = onPress),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        if (isBackspace) {
            Icon(
                Icons.AutoMirrored.Filled.Backspace,
                contentDescription = "Backspace",
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(26.dp),
            )
        } else {
            Text(
                text = key,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )
            if (key.length == 1 && key[0].isDigit() && key != "0") {
                val letters = manualDialLetters(key[0])
                Text(
                    text = letters,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

private fun manualDialLetters(key: Char): String = when (key) {
    '2' -> "ABC"; '3' -> "DEF"; '4' -> "GHI"; '5' -> "JKL"
    '6' -> "MNO"; '7' -> "PQRS"; '8' -> "TUV"; '9' -> "WXYZ"
    else -> ""
}
