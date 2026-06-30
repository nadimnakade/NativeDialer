package com.crm.v2.presentation.ui.settings

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
import com.crm.v2.data.datastore.DarkModePreference

@Composable
fun SettingsScreen(
    onNavigateToProfile: () -> Unit,
    onLogout: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
        )

        // Profile card
        Card(
            onClick = onNavigateToProfile,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(Icons.Filled.AccountCircle, contentDescription = null, modifier = Modifier.size(48.dp), tint = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(uiState.employeeName.ifEmpty { "User" }, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    Text("View profile", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Icon(Icons.Filled.ChevronRight, contentDescription = "View", tint = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }

        // Preferences
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Preferences", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)

                // Dark mode
                Text("Theme", style = MaterialTheme.typography.labelMedium)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    DarkModePreference.entries.forEach { mode ->
                        FilterChip(
                            selected = uiState.darkMode == mode,
                            onClick = { viewModel.setDarkMode(mode) },
                            label = { Text(mode.name.lowercase().replaceFirstChar { it.uppercase() }) },
                        )
                    }
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                // Auto dial interval
                Text("Auto Dial Interval: ${uiState.autoDialInterval}s", style = MaterialTheme.typography.labelMedium)
                Slider(
                    value = uiState.autoDialInterval.toFloat(),
                    onValueChange = { viewModel.setAutoDialInterval(it.toInt()) },
                    valueRange = 5f..30f,
                    steps = 4,
                )

                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                // Call recording
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text("Call Recording", style = MaterialTheme.typography.bodyMedium)
                    Switch(
                        checked = uiState.callRecordingEnabled,
                        onCheckedChange = viewModel::setCallRecordingEnabled,
                    )
                }
            }
        }

        // About
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("About", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Version", style = MaterialTheme.typography.bodyMedium)
                    Text(uiState.appVersion, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }

        // Logout
        OutlinedButton(
            onClick = onLogout,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.error),
        ) {
            Icon(Icons.Filled.Logout, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Logout")
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}
