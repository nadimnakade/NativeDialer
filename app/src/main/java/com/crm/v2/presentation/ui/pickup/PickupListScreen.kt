package com.crm.v2.presentation.ui.pickup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crm.v2.ui.theme.Secondary
import com.crm.v2.domain.model.PickupEntry
import com.crm.v2.presentation.ui.components.CrmEmptyState
import com.crm.v2.presentation.ui.components.CrmLoading

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PickupListScreen(
    onPickupClick: (String) -> Unit,
    onAddPickup: () -> Unit,
    viewModel: PickupViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) { viewModel.loadPickups() }

    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = uiState.searchQuery,
            onValueChange = viewModel::onSearchQueryChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text("Search pickups...") },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
            singleLine = true,
        )

        uiState.error?.let { error ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer),
            ) {
                Text(error, modifier = Modifier.padding(12.dp), color = MaterialTheme.colorScheme.onErrorContainer, style = MaterialTheme.typography.bodySmall)
            }
        }

        if (uiState.isLoading) CrmLoading()
        else if (uiState.entries.isEmpty()) CrmEmptyState("No pickup entries found")
        else LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f),
        ) {
            items(uiState.entries, key = { it.key }) { entry ->
                PickupCard(entry = entry, onClick = { onPickupClick(entry.key) })
            }
        }

        FloatingActionButton(
            onClick = onAddPickup,
            modifier = Modifier
                .padding(16.dp),
            containerColor = Secondary,
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add Pickup", tint = Color.White)
        }
    }
}

@Composable
private fun PickupCard(entry: PickupEntry, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(entry.clientName ?: "Unknown", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                Text(entry.clientMobile ?: "", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    entry.product?.let { Text(it, style = MaterialTheme.typography.labelSmall) }
                    entry.pickupDate?.let { Text(it, style = MaterialTheme.typography.labelSmall) }
                }
            }
            Icon(Icons.Filled.ChevronRight, contentDescription = "Detail", tint = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
