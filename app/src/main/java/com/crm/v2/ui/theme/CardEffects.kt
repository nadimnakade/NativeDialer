package com.crm.v2.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun GradientCard(
    modifier: Modifier = Modifier,
    gradientStart: Color = PurpleBlueGradientStart,
    gradientEnd: Color = PurpleBlueGradientEnd,
    glowColor: Color = CardGlow,
    glowRadius: Dp = 24.dp,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .shadow(
                elevation = glowRadius,
                shape = GradientCardShape,
                ambientColor = glowColor,
                spotColor = glowColor
            )
            .clip(GradientCardShape)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(gradientStart, gradientEnd)
                )
            )
            .border(
                width = 1.dp,
                color = GlassBorder,
                shape = GradientCardShape
            )
    ) {
        content()
    }
}

@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .clip(GlassCardShape)
            .background(GlassSurface)
            .border(
                width = 1.dp,
                color = GlassBorder,
                shape = GlassCardShape
            )
    ) {
        content()
    }
}

@Composable
fun GlowCard(
    modifier: Modifier = Modifier,
    glowColor: Color = Secondary,
    glowRadius: Dp = 20.dp,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .shadow(
                elevation = glowRadius,
                shape = CardShape,
                ambientColor = glowColor.copy(alpha = 0.2f),
                spotColor = glowColor.copy(alpha = 0.3f)
            )
            .clip(CardShape)
            .background(PrimaryContainer)
            .border(
                width = 1.dp,
                color = glowColor.copy(alpha = 0.3f),
                shape = CardShape
            )
    ) {
        content()
    }
}
