package com.example.bookworm.common.ui.composables.animation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import kotlinx.coroutines.delay
import java.text.BreakIterator
import kotlin.random.Random

fun String.toGraphemes(): List<String> {
    val it = BreakIterator.getCharacterInstance()
    it.setText(this)
    val result = mutableListOf<String>()
    var start = it.first()
    var end = it.next()
    while (end != BreakIterator.DONE) {
        result.add(this.substring(start, end))
        start = end
        end = it.next()
    }
    return result
}

@Composable
fun AnimatedText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        text.toGraphemes().forEach { char ->
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

            Text(
                modifier = Modifier.graphicsLayer {
                    alpha = animAlpha
                },
                text = char,
                style = style
            )
        }
    }

}