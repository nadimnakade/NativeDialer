package com.crm.v2.presentation.ui.documentupload

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crm.v2.presentation.ui.components.CrmLoading

@Composable
fun DocumentUploadScreen(
    viewModel: DocumentUploadViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { viewModel.onFileSelected(it.toString()) }
    }

    LaunchedEffect(uiState.success) {
        if (uiState.success != null) { /* reset handled by viewModel */ }
    }

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text("Upload Document", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)

        OutlinedTextField(value = uiState.clientName, onValueChange = viewModel::onClientNameChange, label = { Text("Client Name") }, singleLine = true, modifier = Modifier.fillMaxWidth(), keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next))
        OutlinedTextField(value = uiState.mobileNo, onValueChange = viewModel::onMobileNoChange, label = { Text("Mobile No") }, singleLine = true, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = uiState.fileType, onValueChange = viewModel::onFileTypeChange, label = { Text("File Type") }, singleLine = true, modifier = Modifier.fillMaxWidth(), placeholder = { Text("e.g. Aadhar, PAN, Photo") })

        Button(
            onClick = { launcher.launch("*/*") },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer, contentColor = MaterialTheme.colorScheme.onSecondaryContainer),
        ) {
            Icon(Icons.Filled.Upload, contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text(if (uiState.selectedFileName.isEmpty()) "Select File" else uiState.selectedFileName)
        }

        uiState.error?.let { Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall) }
        uiState.success?.let { Text(it, color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.bodySmall) }

        Button(
            onClick = viewModel::submit,
            modifier = Modifier.fillMaxWidth().height(52.dp),
            enabled = !uiState.isLoading && uiState.selectedFileName.isNotEmpty(),
        ) {
            if (uiState.isLoading) CircularProgressIndicator(modifier = Modifier.size(24.dp), strokeWidth = 2.dp, color = MaterialTheme.colorScheme.onPrimary)
            else { Text("Upload") }
        }
    }
}
