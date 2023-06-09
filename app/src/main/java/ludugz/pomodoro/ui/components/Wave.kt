@file:Suppress("NAME_SHADOWING")

package ludugz.pomodoro.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ludugz.pomodoro.ui.theme.Lima100

/**
 * Created by Tan N. Truong, on 01 June, 2023
 * Email: ludugz@gmail.com
 */

@Composable
fun Wave(
    modifier: Modifier = Modifier,
    initialValue: Dp,
    targetValue: Dp,
    color: Color = Lima100,
    durationMillis: Int = POMODORO_DURATION.toInt(),
    isPlaying: Boolean = false,
) {
    var boxHeight by remember { mutableStateOf(initialValue) }
    LaunchedEffect(key1 = isPlaying) {
        if (isPlaying) {
            launch {
                animate(
                    initialValue = boxHeight.value,
                    targetValue = targetValue.value,
                    animationSpec = tween(durationMillis = durationMillis, easing = LinearEasing)
                ) { value, _ ->
                    boxHeight = value.dp
                }
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height = boxHeight)
            .background(
                color = color
            )
    )
}

@Preview(
    name = "Wave Preview", widthDp = 360, heightDp = 640, showBackground = false
)
@Composable
fun PreviewWaveAnimation() {
    Wave(initialValue = 0.dp, targetValue = 100.dp)
}