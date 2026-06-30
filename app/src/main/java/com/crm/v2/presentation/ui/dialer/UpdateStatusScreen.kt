package com.crm.v2.presentation.ui.dialer

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
import com.crm.v2.common.constants.StatusConstants
import com.crm.v2.common.constants.StatusOption

data class UpdateStatusUiState(
    val selectedStatus: StatusOption? = null,
    val callbackDate: String = "",
    val callbackTime: String = "",
    val isHLLAPBL: String = "",
    val isCibilBad: Boolean = false,
    val isRealEstate: Boolean = false,
    val addDailyLogin: Boolean = false,
    val losNo: String = "",
    val appNo: String = "",
    val banks: String = "",
    val product: String = "",
    val region: String = "",
    val loginAmount: String = "",
    val remarks: String = "",
    val whatsappMessage: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateStatusScreen(
    onSave: (UpdateStatusUiState) -> Unit,
    onCancel: () -> Unit,
) {
    var state by remember { mutableStateOf(UpdateStatusUiState()) }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
    ) {
        Text(
            text = "Update Call Status",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Status Selection
        Text("Call Status *", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(8.dp))

        StatusConstants.allStatuses.chunked(2).forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                row.forEach { status ->
                    FilterChip(
                        selected = state.selectedStatus == status,
                        onClick = { state = state.copy(selectedStatus = status) },
                        label = { Text(status.label, style = MaterialTheme.typography.labelSmall) },
                        modifier = Modifier.weight(1f),
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Follow-up fields (shown when Follow Up or SameDay Follow Up)
        if (state.selectedStatus?.value in listOf(StatusConstants.FOLLOW_UP, StatusConstants.SAME_DAY_FOLLOW_UP)) {
            Spacer(modifier = Modifier.height(16.dp))
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Schedule Follow-up", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(
                        value = state.callbackDate,
                        onValueChange = { state = state.copy(callbackDate = it) },
                        label = { Text("Date") },
                        placeholder = { Text("YYYY-MM-DD") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = state.callbackTime,
                        onValueChange = { state = state.copy(callbackTime = it) },
                        label = { Text("Time") },
                        placeholder = { Text("HH:MM") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }

        // Product selection
        Spacer(modifier = Modifier.height(16.dp))
        Text("Is HL/LAP/BL/PL?", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            listOf("HL", "LAP", "BL", "PL", "CC").forEach { product ->
                FilterChip(
                    selected = state.isHLLAPBL == product,
                    onClick = { state = state.copy(isHLLAPBL = if (state.isHLLAPBL == product) "" else product) },
                    label = { Text(product) },
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = state.isCibilBad, onCheckedChange = { state = state.copy(isCibilBad = it) })
                Text("Cibil Bad", style = MaterialTheme.typography.bodyMedium)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = state.isRealEstate, onCheckedChange = { state = state.copy(isRealEstate = it) })
                Text("Real Estate", style = MaterialTheme.typography.bodyMedium)
            }
        }

        // Daily Login section
        Spacer(modifier = Modifier.height(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = state.addDailyLogin, onCheckedChange = { state = state.copy(addDailyLogin = it) })
            Text("Add Daily Login", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
        }

        if (state.addDailyLogin) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(value = state.losNo, onValueChange = { state = state.copy(losNo = it) }, label = { Text("LOS No") }, singleLine = true, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = state.appNo, onValueChange = { state = state.copy(appNo = it) }, label = { Text("App No") }, singleLine = true, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = state.banks, onValueChange = { state = state.copy(banks = it) }, label = { Text("Banks") }, singleLine = true, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = state.product, onValueChange = { state = state.copy(product = it) }, label = { Text("Product") }, singleLine = true, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = state.region, onValueChange = { state = state.copy(region = it) }, label = { Text("Region") }, singleLine = true, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = state.loginAmount, onValueChange = { state = state.copy(loginAmount = it) }, label = { Text("Login Amount") }, singleLine = true, modifier = Modifier.fillMaxWidth())
                }
            }
        }

        // Remarks
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = state.remarks,
            onValueChange = { state = state.copy(remarks = it) },
            label = { Text("Remarks") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3,
        )

        // WhatsApp
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = state.whatsappMessage,
            onValueChange = { state = state.copy(whatsappMessage = it) },
            label = { Text("WhatsApp Message") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 2,
        )

        // Error
        state.error?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
        }

        // Actions
        Spacer(modifier = Modifier.height(24.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            OutlinedButton(onClick = onCancel, modifier = Modifier.weight(1f)) { Text("Cancel") }
            Button(onClick = { onSave(state) }, modifier = Modifier.weight(1f)) {
                if (state.isLoading) CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
                else Text("Save")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
