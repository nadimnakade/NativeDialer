package com.crm.v2.ui.theme

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween

val StiffSpring = spring<Float>(
    dampingRatio = Spring.DampingRatioMediumBouncy,
    stiffness = Spring.StiffnessMedium,
)

val SmoothSpring = spring<Float>(
    dampingRatio = Spring.DampingRatioLowBouncy,
    stiffness = Spring.StiffnessLow,
)

val SnappySpring = spring<Float>(
    dampingRatio = Spring.DampingRatioNoBouncy,
    stiffness = Spring.StiffnessMedium,
)

val SoftSpring = spring<Float>(
    dampingRatio = Spring.DampingRatioHighBouncy,
    stiffness = Spring.StiffnessVeryLow,
)

val DefaultTween = tween<Float>(
    durationMillis = 300,
    easing = FastOutSlowInEasing,
)

val SlowTween = tween<Float>(
    durationMillis = 600,
    easing = FastOutSlowInEasing,
)

val QuickFadeIn = tween<Float>(
    durationMillis = 200,
    easing = FastOutSlowInEasing,
)

val SlideInFromBottom = tween<Float>(
    durationMillis = 400,
    easing = FastOutSlowInEasing,
)

val SmoothLinearTween = tween<Float>(
    durationMillis = 500,
    easing = LinearEasing,
)
