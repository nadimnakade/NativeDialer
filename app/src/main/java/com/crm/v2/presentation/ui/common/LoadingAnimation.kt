package com.crm.v2.presentation.ui.common

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun LoadingOverlay(
    isVisible: Boolean,
    message: String = "Loading...",
) {
    if (isVisible) {
        Dialog(
            onDismissRequest = {},
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
        ) {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            ) {
                Column(
                    modifier = Modifier.padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    LoadingDotsAnimation()
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = message,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }
    }
}

@Composable
fun LoadingDotsAnimation(
    dotColor: Color = MaterialTheme.colorScheme.primary,
    dotSize: Float = 10f,
) {
    val delays = listOf(0, 150, 300)

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        delays.forEachIndexed { index, delay ->
            val infiniteTransition = rememberInfiniteTransition(label = "dot_$index")
            val scale by infiniteTransition.animateFloat(
                initialValue = 0.4f,
                targetValue = 1.2f,
                animationSpec = infiniteRepeatable(
                    animation = tween(500, delayMillis = delay, easing = FastOutSlowInEasing),
                    repeatMode = RepeatMode.Reverse,
                ),
                label = "scale_$index",
            )
            val alpha by infiniteTransition.animateFloat(
                initialValue = 0.3f,
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(500, delayMillis = delay, easing = FastOutSlowInEasing),
                    repeatMode = RepeatMode.Reverse,
                ),
                label = "alpha_$index",
            )

            Box(
                modifier = Modifier
                    .size(dotSize.dp)
                    .scale(scale)
                    .alpha(alpha)
                    .clip(RoundedCornerShape(50))
                    .background(dotColor),
            )
        }
    }
}

@Composable
fun PulseLoadingAnimation(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "scale",
    )
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "alpha",
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .scale(scale)
                .alpha(alpha)
                .clip(RoundedCornerShape(50))
                .background(color.copy(alpha = 0.2f)),
        )
        CircularProgressIndicator(
            modifier = Modifier.size(32.dp),
            color = color,
            strokeWidth = 3.dp,
        )
    }
}

@Composable
fun ShimmerLoadingItem(
    modifier: Modifier = Modifier,
) {
    val shimmerTransition = rememberInfiniteTransition(label = "shimmer")
    val alpha by shimmerTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "alpha",
    )

    Column(
        modifier = modifier.padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(16.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = alpha)),
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .height(12.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = alpha * 0.7f)),
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .height(12.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = alpha * 0.5f)),
        )
    }
}
