package com.crm.v2.presentation.ui.pickup

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhoneInTalk
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.crm.v2.domain.model.PickupEntry

@Composable
fun PickupDetailScreen(entry: PickupEntry?, onCall: (String) -> Unit) {
    if (entry == null) return

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(entry.clientName ?: "Unknown", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                DetailRow("Mobile", entry.clientMobile ?: "")
                DetailRow("Product", entry.product ?: "")
                DetailRow("Bank", entry.banks ?: "")
                DetailRow("Status", entry.status ?: "")
                DetailRow("Date", entry.pickupDate ?: "")
                DetailRow("Region", entry.region ?: "")
                DetailRow("Type", entry.pickupType ?: "")
                DetailRow("Caller", entry.callerName ?: "")
            }
        }

        Button(
            onClick = { entry.clientMobile?.let(onCall) },
            modifier = Modifier.fillMaxWidth().height(52.dp),
        ) {
            Icon(Icons.Filled.PhoneInTalk, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Call ${entry.clientName}")
        }
    }
}

@Composable
private fun DetailRow(label: String, value: String) {
    Row(Modifier.fillMaxWidth().padding(vertical = 4.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
    }
}
