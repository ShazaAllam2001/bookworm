package com.example.bookworm.feature.splash.ui.composables

import android.content.Context
import android.content.res.Configuration
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import kotlinx.coroutines.delay
import kotlin.math.min
import kotlin.random.Random

fun Context.getDotBackgroundColor(): Long {
    return when(resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
        Configuration.UI_MODE_NIGHT_YES -> 0xFF165B91
        Configuration.UI_MODE_NIGHT_NO -> 0xFF3E8BDC
        else -> 0xFF165B91
    }
}

@Composable
fun BubblesBackground() {
    val density = LocalDensity.current
    val context = LocalContext.current

    BoxWithConstraints(Modifier.fillMaxSize()) {
        with(density) {
            val maxWidth = this@BoxWithConstraints.maxWidth
            val maxHeight = this@BoxWithConstraints.maxHeight

            for (i in 1..50) {
                var state by remember { mutableStateOf(false) }
                val centerX = remember { Random.nextInt(0, maxWidth.toPx().toInt()).toFloat() }
                val centerY = remember {  Random.nextInt(0, maxHeight.toPx().toInt()).toFloat() }
                val radius = remember {
                    Random.nextInt(16, min(maxWidth.toPx(), maxHeight.toPx()).toInt() / 14).toFloat()
                }
                val alpha = remember {  Random.nextInt(10, 85) / 100f }

                LaunchedEffect(Unit) {
                    while(true) {
                        delay(Random.nextLong(300, 5000))
                        state = true
                        delay(3600)
                        state = false
                        delay(3600)
                    }
                }

                val animScale by animateFloatAsState(
                    targetValue = if (state) 1f else .75f,
                    animationSpec = tween(
                        delayMillis = 12000,
                        easing = LinearEasing
                    )
                )
                val animCenterX by animateFloatAsState(
                    targetValue = if (state) .8f else 1f,
                    animationSpec = tween(
                        delayMillis = 9000,
                        easing = FastOutSlowInEasing
                    )
                )
                val animCenterY by animateFloatAsState(
                    targetValue = if (state) .8f else 1f,
                    animationSpec = tween(
                        delayMillis = 9000,
                        easing = FastOutSlowInEasing
                    )
                )

                Canvas(Modifier.fillMaxSize()) {
                    drawCircle(
                        color = Color(context.getDotBackgroundColor()),
                        center = Offset(
                            x = if (i % 2 != 0) centerX * animCenterX else centerX,
                            y = if (i % 2 == 0) centerY * animCenterY else centerY
                        ),
                        radius = radius * animScale,
                        alpha = alpha
                    )
                }
            }
        }
    }
}