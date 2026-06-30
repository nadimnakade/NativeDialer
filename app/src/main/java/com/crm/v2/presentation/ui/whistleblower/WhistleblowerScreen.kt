package com.crm.v2.presentation.ui.whistleblower

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Report
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun WhistleblowerScreen(
    viewModel: WhistleblowerViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Filled.Report, contentDescription = null, tint = MaterialTheme.colorScheme.error)
            Spacer(Modifier.width(8.dp))
            Text("Whistleblower Report", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
        }

        OutlinedTextField(value = uiState.description, onValueChange = viewModel::onDescriptionChange, label = { Text("Describe the issue in detail") }, modifier = Modifier.fillMaxWidth().height(200.dp), maxLines = 10)

        OutlinedTextField(value = uiState.department, onValueChange = viewModel::onDepartmentChange, label = { Text("Department (optional)") }, singleLine = true, modifier = Modifier.fillMaxWidth())

        uiState.error?.let { Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall) }
        uiState.success?.let { Text(it, color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.bodySmall) }

        Button(
            onClick = viewModel::submit,
            modifier = Modifier.fillMaxWidth().height(52.dp),
            enabled = !uiState.isLoading && uiState.description.isNotBlank(),
        ) {
            if (uiState.isLoading) CircularProgressIndicator(Modifier.size(24.dp), strokeWidth = 2.dp, color = MaterialTheme.colorScheme.onPrimary)
            else { Text("Submit Report") }
        }
    }
}
