package com.crm.v2.presentation.ui.leads

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crm.v2.presentation.ui.components.CrmLoading
import com.crm.v2.presentation.ui.components.CrmErrorState
import com.crm.v2.presentation.ui.components.StatusBadge

@Composable
fun LeadDetailScreen(
    onCall: (String) -> Unit,
    onBack: () -> Unit,
    viewModel: LeadDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.isLoading -> CrmLoading()
        uiState.error != null -> CrmErrorState(message = uiState.error!!, onRetry = viewModel::loadLead)
        uiState.lead != null -> {
            val lead = uiState.lead!!
            Column(
                modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(lead.clientName, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Filled.Phone, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(lead.contact, style = MaterialTheme.typography.titleMedium)
                        }
                        lead.status?.let {
                            Spacer(modifier = Modifier.height(8.dp))
                            StatusBadge(status = it)
                        }
                    }
                }

                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("Details", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        lead.product?.let { DetailRow("Product", it) }
                        lead.company?.let { DetailRow("Company", it) }
                        lead.salary?.let { DetailRow("Salary", it) }
                        lead.callbackDate?.let { DetailRow("Callback", it) }
                        lead.callerName?.let { DetailRow("Caller", it) }
                        DetailRow("Cibil Bad", if (lead.isCibilBad) "Yes" else "No")
                        DetailRow("Real Estate", if (lead.isRealEstate) "Yes" else "No")
                    }
                }

                lead.remarks?.let { remarks ->
                    if (remarks.isNotBlank()) {
                        Card(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text("Remarks", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(remarks, style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                    }
                }

                Button(
                    onClick = { onCall(lead.contact) },
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                ) {
                    Icon(Icons.Filled.PhoneInTalk, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Call ${lead.clientName}")
                }
            }
        }
    }
}

@Composable
private fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(label, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.SemiBold)
    }
}
