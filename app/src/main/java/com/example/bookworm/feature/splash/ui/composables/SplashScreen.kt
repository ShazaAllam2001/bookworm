package com.example.bookworm.feature.splash.ui.composables

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.bookworm.R
import com.example.bookworm.common.ui.composables.animation.AnimatedText
import com.example.bookworm.ui.theme.dimens
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun SplashScreen(onNavigateToLogin: () -> Unit) {
    BubblesBackground()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var state by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            while(true) {
                val startDelay = Random.nextLong(300, 900)
                delay(startDelay)
                state = true
                delay(3000)
                state = false
                delay(2000 - startDelay)
            }
        }

        val animAlpha by animateFloatAsState(
            targetValue = if (state) 1f else 0f,
            animationSpec = tween(
                delayMillis = 900,
                easing = LinearEasing
            )
        )
        val animRotation by animateFloatAsState(
            targetValue = if (state) 360f else 0f,
            animationSpec = tween(
                delayMillis = 900,
                easing = FastOutSlowInEasing
            )
        )
        val animScale by animateFloatAsState(
            targetValue = if (state) 1f else 0f,
            animationSpec = tween(
                delayMillis = 900,
                easing = FastOutSlowInEasing
            )
        )

        Image(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .clip(CircleShape)
                .graphicsLayer {
                    alpha = animAlpha
                    rotationX = animRotation
                    rotationY = animRotation
                    rotationZ = animRotation
                    scaleX = animScale
                    scaleY = animScale
                },
            painter = painterResource(R.drawable.bookworm_logo),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        AnimatedText(
            modifier = Modifier.fillMaxWidth()
                .padding(MaterialTheme.dimens.paddingMedium),
            text = stringResource(R.string.a_library_in_your_pocket),
            style = MaterialTheme.typography.headlineMedium
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.dimens.paddingLarge),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ElevatedButton(
            border = BorderStroke(
                MaterialTheme.dimens.thicknessExtraSmall,
                MaterialTheme.colorScheme.onBackground
            ),
            onClick = { onNavigateToLogin() }
        ) {
            Text(
                text = stringResource(R.string.get_started),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}
