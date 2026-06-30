package com.crm.v2.presentation.ui.dialer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.crm.v2.telephony.CallState

@Composable
fun ActiveCallScreen(
    number: String,
    callState: CallState,
    onHangup: () -> Unit,
    onMute: () -> Unit,
    onSpeaker: () -> Unit,
    onHold: () -> Unit,
    onNavigateToUpdateStatus: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        // Call info
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = number,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = callState.label,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "00:00",
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Call controls
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            CallControlButton(icon = Icons.Filled.MicOff, label = "Mute", onClick = onMute)
            CallControlButton(icon = Icons.Filled.Speaker, label = "Speaker", onClick = onSpeaker)
            CallControlButton(icon = Icons.Filled.Pause, label = "Hold", onClick = onHold)
            CallControlButton(icon = Icons.Filled.Dialpad, label = "Keypad", onClick = {})
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Update status button (enabled after call ends)
        if (callState is CallState.Idle || callState is CallState.Error) {
            Button(
                onClick = onNavigateToUpdateStatus,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text("Update Call Status", style = MaterialTheme.typography.titleMedium)
            }
        }

        // Hangup button
        Button(
            onClick = {
                if (callState.isActive) onHangup()
                else onNavigateToUpdateStatus()
            },
            modifier = Modifier
                .size(72.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error,
            ),
        ) {
            Icon(
                Icons.Filled.CallEnd,
                contentDescription = "Hangup",
                modifier = Modifier.size(32.dp),
            )
        }
    }
}

@Composable
private fun CallControlButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    onClick: () -> Unit,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        FilledTonalIconButton(onClick = onClick) {
            Icon(icon, contentDescription = label)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(label, style = MaterialTheme.typography.labelSmall)
    }
}
