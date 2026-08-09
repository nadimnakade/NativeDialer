package com.crm.v2.presentation.ui.dialer

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.crm.v2.common.util.maskPhone
import com.crm.v2.telephony.CallState
import kotlinx.coroutines.delay

@Composable
fun ActiveCallScreen(
    number: String,
    clientName: String = "",
    callState: CallState,
    isMuted: Boolean = false,
    isSpeakerOn: Boolean = false,
    isOnHold: Boolean = false,
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

    val pulseAnim = rememberInfiniteTransition(label = "pulse")
    val pulseScale by pulseAnim.animateFloat(
        initialValue = 1f,
        targetValue = 1.15f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "pulseScale",
    )

    val statusColor = when {
        callState is CallState.Connected && !isOnHold -> Color(0xFF10B981)
        callState is CallState.OnHold || isOnHold -> Color(0xFFF59E0B)
        callState is CallState.Calling -> Color(0xFF6366F1)
        callState is CallState.Ringing -> Color(0xFF10B981)
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }

    val statusLabel = when {
        callState is CallState.Connected && !isOnHold -> "Connected"
        callState is CallState.OnHold || isOnHold -> "On Hold"
        callState is CallState.Calling -> "Calling..."
        callState is CallState.Ringing -> "Ringing..."
        callState is CallState.Registering -> "Registering..."
        callState is CallState.Error -> "Call Failed"
        else -> "Connecting..."
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.surface,
                        MaterialTheme.colorScheme.surface,
                        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                    )
                )
            ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(statusColor.copy(alpha = 0.12f)),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .scale(if (callState is CallState.Calling) pulseScale else 1f),
                        tint = statusColor,
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (clientName.isNotEmpty()) {
                    Text(
                        text = clientName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }

                Text(
                    text = number.maskPhone(),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Surface(
                    shape = MaterialTheme.shapes.medium,
                    color = statusColor.copy(alpha = 0.12f),
                ) {
                    Text(
                        text = statusLabel,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = statusColor,
                    )
                }

                if (isCallActive) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = timerText,
                        style = MaterialTheme.typography.displaySmall.copy(fontSize = 40.sp),
                        fontWeight = FontWeight.Thin,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    CallControlButton(
                        icon = if (isMuted) Icons.Filled.Mic else Icons.Filled.MicOff,
                        label = if (isMuted) "Unmute" else "Mute",
                        isSelected = isMuted,
                        onClick = onMute,
                    )
                    CallControlButton(
                        icon = Icons.Filled.Speaker,
                        label = if (isSpeakerOn) "Earpiece" else "Speaker",
                        isSelected = isSpeakerOn,
                        onClick = onSpeaker,
                    )
                    CallControlButton(
                        icon = if (isOnHold) Icons.Filled.PlayArrow else Icons.Filled.Pause,
                        label = if (isOnHold) "Resume" else "Hold",
                        isSelected = isOnHold,
                        onClick = onHold,
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        if (callState.isActive) onHangup()
                        else onNavigateToUpdateStatus()
                    },
                    modifier = Modifier.size(64.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
                ) {
                    Icon(
                        if (callState.isActive) Icons.Filled.CallEnd else Icons.Filled.NavigateNext,
                        contentDescription = if (callState.isActive) "Hangup" else "Next",
                        modifier = Modifier.size(28.dp),
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = if (callState.isActive) "End Call" else "Continue",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun CallControlButton(
    icon: ImageVector,
    label: String,
    isSelected: Boolean = false,
    onClick: () -> Unit,
) {
    val containerColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.surfaceVariant,
        label = "btnColor",
    )
    val contentColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.onPrimary
        else MaterialTheme.colorScheme.onSurfaceVariant,
        label = "btnContent",
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(64.dp),
    ) {
        FilledTonalIconButton(
            onClick = onClick,
            colors = IconButtonDefaults.filledTonalIconButtonColors(
                containerColor = containerColor,
                contentColor = contentColor,
            ),
            modifier = Modifier.size(48.dp),
        ) {
            Icon(icon, contentDescription = label, modifier = Modifier.size(22.dp))
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            label,
            style = MaterialTheme.typography.labelSmall,
            color = contentColor,
            textAlign = TextAlign.Center,
        )
    }
}
