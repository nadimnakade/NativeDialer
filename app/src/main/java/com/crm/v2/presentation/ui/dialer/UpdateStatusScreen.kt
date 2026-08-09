package com.crm.v2.presentation.ui.dialer

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.crm.v2.common.constants.StatusConstants
import com.crm.v2.common.constants.StatusOption
import java.util.Calendar

data class UpdateStatusUiState(
    val leadKey: String = "",
    val clientName: String = "",
    val mobileNo: String = "",
    val callDuration: Int = 0,
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
    leadKey: String = "",
    clientName: String = "",
    mobileNo: String = "",
    callDuration: Int = 0,
    onSave: (UpdateStatusUiState) -> Unit,
    onCancel: () -> Unit,
) {
    var state by remember {
        mutableStateOf(
            UpdateStatusUiState(
                leadKey = leadKey,
                clientName = clientName,
                mobileNo = mobileNo,
                callDuration = callDuration,
            )
        )
    }
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
    ) {
        Text(
            text = "Update Call Status",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
        )

        if (leadKey.isEmpty()) {
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = state.clientName,
                onValueChange = { state = state.copy(clientName = it) },
                label = { Text("Client Name *") },
                placeholder = { Text("Enter client name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
            )
        }

        if (clientName.isNotEmpty() || mobileNo.isNotEmpty()) {
            Spacer(modifier = Modifier.height(12.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    if (clientName.isNotEmpty()) {
                        Text(
                            clientName,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                        )
                    }
                    if (mobileNo.isNotEmpty()) {
                        Text(
                            mobileNo,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                    if (callDuration > 0) {
                        val mins = callDuration / 60
                        val secs = callDuration % 60
                        Text(
                            "Call Duration: ${String.format("%02d:%02d", mins, secs)}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Medium,
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text("Call Status *", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(8.dp))

        val isCallConnected = state.callDuration > 0
        val availableStatuses = if (isCallConnected) StatusConstants.connectedStatuses else StatusConstants.notConnectedStatuses

        if (!isCallConnected) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                ),
            ) {
                Text(
                    "Call was not connected. Only Ringing/Not Contactable available.",
                    modifier = Modifier.padding(10.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        availableStatuses.chunked(3).forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
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
            Spacer(modifier = Modifier.height(6.dp))
        }

        if (state.selectedStatus?.value in listOf(StatusConstants.FOLLOW_UP, StatusConstants.SAME_DAY_FOLLOW_UP)) {
            Spacer(modifier = Modifier.height(12.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text("Schedule Follow-up", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        OutlinedTextField(
                            value = state.callbackDate,
                            onValueChange = { state = state.copy(callbackDate = it) },
                            label = { Text("Date") },
                            placeholder = { Text("DD/MM/YYYY") },
                            singleLine = true,
                            modifier = Modifier.weight(1f),
                            trailingIcon = {
                                IconButton(onClick = {
                                    val cal = Calendar.getInstance()
                                    DatePickerDialog(
                                        context,
                                        { _, year, month, day ->
                                            state = state.copy(callbackDate = String.format("%02d/%02d/%04d", day, month + 1, year))
                                        },
                                        cal.get(Calendar.YEAR),
                                        cal.get(Calendar.MONTH),
                                        cal.get(Calendar.DAY_OF_MONTH),
                                    ).show()
                                }) {
                                    Icon(Icons.Filled.CalendarMonth, contentDescription = "Pick date")
                                }
                            },
                        )
                        OutlinedTextField(
                            value = state.callbackTime,
                            onValueChange = { state = state.copy(callbackTime = it) },
                            label = { Text("Time") },
                            placeholder = { Text("HH:MM") },
                            singleLine = true,
                            modifier = Modifier.weight(1f),
                            trailingIcon = {
                                IconButton(onClick = {
                                    val cal = Calendar.getInstance()
                                    TimePickerDialog(
                                        context,
                                        { _, hour, minute ->
                                            state = state.copy(callbackTime = String.format("%02d:%02d", hour, minute))
                                        },
                                        cal.get(Calendar.HOUR_OF_DAY),
                                        cal.get(Calendar.MINUTE),
                                        false,
                                    ).show()
                                }) {
                                    Icon(Icons.Filled.Schedule, contentDescription = "Pick time")
                                }
                            },
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text("Is HL/LAP/BL/PL?", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(6.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            listOf("HL", "LAP", "BL", "PL", "CC").forEach { product ->
                FilterChip(
                    selected = state.isHLLAPBL == product,
                    onClick = { state = state.copy(isHLLAPBL = if (state.isHLLAPBL == product) "" else product) },
                    label = { Text(product) },
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
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

        Spacer(modifier = Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = state.addDailyLogin, onCheckedChange = { state = state.copy(addDailyLogin = it) })
            Text("Add Daily Login", style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.SemiBold)
        }

        if (state.addDailyLogin) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
            ) {
                Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    OutlinedTextField(value = state.losNo, onValueChange = { state = state.copy(losNo = it) }, label = { Text("LOS No") }, singleLine = true, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = state.appNo, onValueChange = { state = state.copy(appNo = it) }, label = { Text("App No") }, singleLine = true, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = state.banks, onValueChange = { state = state.copy(banks = it) }, label = { Text("Banks") }, singleLine = true, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = state.product, onValueChange = { state = state.copy(product = it) }, label = { Text("Product") }, singleLine = true, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = state.region, onValueChange = { state = state.copy(region = it) }, label = { Text("Region") }, singleLine = true, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = state.loginAmount, onValueChange = { state = state.copy(loginAmount = it) }, label = { Text("Login Amount") }, singleLine = true, modifier = Modifier.fillMaxWidth())
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = state.remarks,
            onValueChange = { state = state.copy(remarks = it) },
            label = { Text("Remarks") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 2,
            maxLines = 4,
            shape = RoundedCornerShape(12.dp),
        )

        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = state.whatsappMessage,
            onValueChange = { state = state.copy(whatsappMessage = it) },
            label = { Text("WhatsApp Message") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 2,
            maxLines = 3,
            shape = RoundedCornerShape(12.dp),
        )

        state.error?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
        }

        Spacer(modifier = Modifier.height(24.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            OutlinedButton(
                onClick = onCancel,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(10.dp),
            ) { Text("Cancel") }
            Button(
                onClick = {
                    if (leadKey.isEmpty() && state.clientName.isBlank()) {
                        state = state.copy(error = "Please Enter Client Name")
                    } else if (state.selectedStatus == null) {
                        state = state.copy(error = "Please select a status")
                    } else {
                        val selected = state.selectedStatus!!
                        val connected = state.callDuration > 0
                        val statusValue = selected.value
                        val isRingingOrNC = statusValue == StatusConstants.RINGING || statusValue == StatusConstants.NOT_CONTACTABLE

                        if (!connected && !isRingingOrNC) {
                            state = state.copy(error = "Call was not connected. Only Ringing or Not Contactable allowed.")
                        } else if (connected && isRingingOrNC) {
                            state = state.copy(error = "Call was connected. Cannot select Ringing or Not Contactable.")
                        } else {
                            state = state.copy(error = null)
                            onSave(state)
                        }
                    }
                },
                modifier = Modifier.weight(1f),
                enabled = state.selectedStatus != null && !state.isLoading,
                shape = RoundedCornerShape(10.dp),
            ) {
                if (state.isLoading) CircularProgressIndicator(modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
                else Text("Update", fontWeight = FontWeight.SemiBold)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
