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
import kotlinx.coroutines.delay

@Composable
fun ActiveCallScreen(
    number: String,
    clientName: String = "",
    callState: CallState,
    onHangup: () -> Unit,
    onMute: () -> Unit,
    onSpeaker: () -> Unit,
    onHold: () -> Unit,
    onNavigateToUpdateStatus: () -> Unit,
) {
    var elapsedSeconds by remember { mutableIntStateOf(0) }

    val isCallActive = callState is CallState.Connected || callState is CallState.OnHold

    LaunchedEffect(isCallActive) {
        if (isCallActive) {
            while (true) {
                delay(1000L)
                elapsedSeconds++
            }
        }
    }

    LaunchedEffect(callState) {
        if (callState is CallState.Calling || callState is CallState.Ringing) {
            elapsedSeconds = 0
        }
    }

    val minutes = elapsedSeconds / 60
    val seconds = elapsedSeconds % 60
    val timerText = String.format("%02d:%02d", minutes, seconds)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if (clientName.isNotEmpty()) {
                Text(
                    text = clientName,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            Text(
                text = number,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = callState.label,
                style = MaterialTheme.typography.titleMedium,
                color = when (callState) {
                    is CallState.Connected -> MaterialTheme.colorScheme.primary
                    is CallState.OnHold -> MaterialTheme.colorScheme.tertiary
                    is CallState.Calling -> MaterialTheme.colorScheme.onSurfaceVariant
                    is CallState.Ringing -> MaterialTheme.colorScheme.primary
                    else -> MaterialTheme.colorScheme.onSurfaceVariant
                },
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (isCallActive) {
                Text(
                    text = timerText,
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

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

        Button(
            onClick = {
                if (callState.isActive) onHangup()
                else onNavigateToUpdateStatus()
            },
            modifier = Modifier.size(72.dp),
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

        Spacer(modifier = Modifier.height(12.dp))
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
