package ludugz.pomodoro.ui.components

import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ludugz.pomodoro.R

/**
 * Created by Tan N. Truong, on 23 May, 2023
 * Email: ludugz@gmail.com
 */
@Composable
fun Clock(
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    onClick: () -> Unit = {},
) {
    val coroutineScope = rememberCoroutineScope()
    var alpha by remember { mutableStateOf(1f) }
    var resource by remember { mutableStateOf(R.drawable.play_circle_filled_24) }
    var isPlaying by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .wrapContentSize()
            .clickable {
                onClick()
                isPlaying = !isPlaying
                coroutineScope.launch {
                    animate(
                        initialValue = 1f,
                        targetValue = 0f,
                        animationSpec = tween(durationMillis = 500),
                        block = { value, _ ->
                            alpha = value
                            if (value == 0f) {
                                resource = if (isPlaying) {
                                    R.drawable.pause_circle_filled_24
                                } else {
                                    R.drawable.play_circle_filled_24
                                }
                            }
                        }
                    )
                    animate(
                        initialValue = 0f,
                        targetValue = 1f,
                        animationSpec = tween(durationMillis = 500),
                        block = { value, _ ->
                            alpha = value
                            if (value == 1f) {
                                resource = if (isPlaying) {
                                    R.drawable.pause_circle_filled_24
                                } else {
                                    R.drawable.play_circle_filled_24
                                }
                            }
                        }
                    )
                }
            },
        contentAlignment = Alignment.Center
    ) {
        BorderOuterCircle(
            circleSize = 300.dp,
            thickness = 10.dp,
            color = color,
        )
        AnimatedPlayButton(
            resource = resource,
            alpha = alpha,
        )
    }
}

@Composable
fun AnimatedPlayButton(
    resource: Int = R.drawable.play_circle_filled_24,
    alpha: Float = 1f,
) {
    Image(
        painter = painterResource(id = resource),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .size(100.dp)
            .alpha(alpha = alpha)
    )
}

@Composable
fun BorderOuterCircle(
    modifier: Modifier = Modifier,
    circleSize: Dp = 300.dp,
    color: Color = Color.White,
    thickness: Dp = 16.dp,
) {
    val strokeWidth = with(LocalDensity.current) { thickness.toPx() }
    Canvas(modifier.size(size = circleSize)) {
        drawCircle(
            color = color,
            radius = size.minDimension / 2,
            center = Offset(size.width / 2, size.height / 2),
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        )
    }
}

@Preview(name = "Clock", backgroundColor = 0xFFcdeda5, showBackground = true)
@Composable
fun PreviewClock() {
    Clock(onClick = { })
}

@Preview(
    name = "Play Button",
    backgroundColor = 0xFFcdeda5,
    showBackground = true,
    widthDp = 150,
    heightDp = 150
)
@Composable
fun PreviewAnimatedPlayButton() {
    AnimatedPlayButton()
}