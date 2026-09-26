package com.crm.v2.presentation.ui.dailylogin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crm.v2.presentation.ui.components.CrmEmptyState
import com.crm.v2.presentation.ui.components.CrmLoading

@Composable
fun DailyLoginScreen(
    viewModel: DailyLoginViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val tabs = listOf("Add Entry", "History")

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(selectedTabIndex = uiState.activeTab) {
            tabs.forEachIndexed { i, title ->
                Tab(selected = uiState.activeTab == i, onClick = { viewModel.setActiveTab(i) }, text = { Text(title) })
            }
        }

        when (uiState.activeTab) {
            0 -> DailyLoginForm(uiState = uiState, viewModel = viewModel)
            1 -> DailyLoginList(uiState = uiState, viewModel = viewModel)
        }
    }
}

@Composable
private fun DailyLoginForm(uiState: DailyLoginUiState, viewModel: DailyLoginViewModel) {
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text("Add Daily Login", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)

        OutlinedTextField(value = uiState.clientName, onValueChange = { viewModel.onFieldChange("clientName", it) }, label = { Text("Client Name *") }, singleLine = true, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = uiState.mobileNo, onValueChange = { viewModel.onFieldChange("mobileNo", it) }, label = { Text("Mobile No *") }, singleLine = true, modifier = Modifier.fillMaxWidth(), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone))
        OutlinedTextField(value = uiState.banks, onValueChange = { viewModel.onFieldChange("banks", it) }, label = { Text("Banks") }, singleLine = true, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = uiState.product, onValueChange = { viewModel.onFieldChange("product", it) }, label = { Text("Product") }, singleLine = true, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = uiState.region, onValueChange = { viewModel.onFieldChange("region", it) }, label = { Text("Region") }, singleLine = true, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = uiState.losNo, onValueChange = { viewModel.onFieldChange("losNo", it) }, label = { Text("LOS No") }, singleLine = true, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = uiState.appNo, onValueChange = { viewModel.onFieldChange("appNo", it) }, label = { Text("App No") }, singleLine = true, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = uiState.loginAmount, onValueChange = { viewModel.onFieldChange("loginAmount", it) }, label = { Text("Login Amount") }, singleLine = true, modifier = Modifier.fillMaxWidth(), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal))
        OutlinedTextField(value = uiState.remarks, onValueChange = { viewModel.onFieldChange("remarks", it) }, label = { Text("Remarks") }, modifier = Modifier.fillMaxWidth(), minLines = 2)

        uiState.error?.let { Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall) }

        if (uiState.isSuccess) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            ) {
                Text("Daily login saved successfully", modifier = Modifier.padding(12.dp), color = MaterialTheme.colorScheme.onPrimaryContainer, style = MaterialTheme.typography.bodySmall)
            }
        }

        Button(
            onClick = viewModel::submit,
            modifier = Modifier.fillMaxWidth().height(52.dp),
            enabled = !uiState.isLoading,
        ) {
            if (uiState.isLoading) CircularProgressIndicator(modifier = Modifier.size(24.dp), strokeWidth = 2.dp, color = MaterialTheme.colorScheme.onPrimary)
            else { Icon(Icons.Filled.Check, contentDescription = null); Spacer(Modifier.width(8.dp)); Text("Save") }
        }
    }
}

@Composable
private fun DailyLoginList(uiState: DailyLoginUiState, viewModel: DailyLoginViewModel) {
    LaunchedEffect(Unit) { viewModel.loadList() }

    if (uiState.isLoading) CrmLoading()
    else if (uiState.list.isEmpty()) CrmEmptyState("No daily login entries")
    else LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(uiState.list, key = { it.key ?: "" }) { entry ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(entry.clientName ?: "", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                    Spacer(Modifier.height(4.dp))
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("₹${entry.loginAmount.toInt()}", style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                        Text(entry.product ?: "", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }
        }
    }
}
