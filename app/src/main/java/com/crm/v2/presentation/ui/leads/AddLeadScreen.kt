package com.crm.v2.presentation.ui.leads

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AddLeadScreen(
    onSuccess: () -> Unit,
    onBack: () -> Unit,
    viewModel: AddLeadViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) onSuccess()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
    ) {
        Text(
            text = "Add New Lead",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = uiState.name,
            onValueChange = viewModel::onNameChanged,
            label = { Text("Name *") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = uiState.mobileNo,
            onValueChange = viewModel::onMobileChanged,
            label = { Text("Mobile Number *") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Next),
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = uiState.product,
            onValueChange = viewModel::onProductChanged,
            label = { Text("Product *") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = uiState.remarks,
            onValueChange = viewModel::onRemarksChanged,
            label = { Text("Remarks") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        )

        Spacer(modifier = Modifier.height(12.dp))

        uiState.error?.let { error ->
            Text(error, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = viewModel::submit,
            modifier = Modifier.fillMaxWidth().height(52.dp),
            enabled = !uiState.isLoading,
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp), strokeWidth = 2.dp, color = MaterialTheme.colorScheme.onPrimary)
                Spacer(modifier = Modifier.width(8.dp))
            }
            Icon(Icons.Filled.Check, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Save Lead")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Cancel")
        }
    }
}
