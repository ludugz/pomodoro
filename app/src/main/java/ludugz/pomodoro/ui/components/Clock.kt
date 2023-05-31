package ludugz.pomodoro.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import ludugz.pomodoro.R

/**
 * Created by Tan N. Truong, on 23 May, 2023
 * Email: ludugz@gmail.com
 */
@Composable
fun Clock(modifier: Modifier = Modifier) {
    var isPlayButtonVisible by remember { mutableStateOf(true) }
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .wrapContentSize()
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                isPlayButtonVisible = !isPlayButtonVisible
            },
        contentAlignment = Alignment.Center
    ) {
        BorderOuterCircle(
            modifier = modifier
                .width(300.dp)
                .height(300.dp)
                .clip(shape = CircleShape)
        )
        AnimatedPlayButton(shouldPlay = isPlayButtonVisible)

    }
}

@Composable
fun AnimatedPlayButton(
    shouldPlay: Boolean,
) {
    val isPlaying = remember { mutableStateOf(shouldPlay) }
    AnimatedVisibility(
        visible = shouldPlay,
        enter = fadeIn(animationSpec = tween(durationMillis = 500)),
        exit = fadeOut(animationSpec = tween(durationMillis = 500)),
    ) {
        Image(
            painter = painterResource(id = R.drawable.play_circle_filled_24),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(100.dp)
        )
    }
    AnimatedVisibility(
        visible = !shouldPlay,
        enter = fadeIn(animationSpec = tween(durationMillis = 500, delayMillis = 500)),
        exit = fadeOut(animationSpec = tween(durationMillis = 500, delayMillis = 500)),
    ) {
        Image(
            painter = painterResource(id = R.drawable.pause_circle_filled_24),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(100.dp)
        )
    }

}

@Composable
fun BorderOuterCircle(
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    thickness: Dp = 16.dp,
) {
    val strokeWidth = with(LocalDensity.current) { thickness.toPx() }
    Canvas(modifier) {
        drawCircle(
            color = color,
            radius = size.minDimension / 2,
            center = Offset(size.width / 2, size.height / 2),
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
        )
    }
}

@Preview(backgroundColor = 0xFFcdeda5, showBackground = true)
@Composable
fun PreviewClock() {
    Clock()
}

@Preview(backgroundColor = 0xFFcdeda5, showBackground = true)
@Composable
fun PreviewAnimatedPlayButton() {
    AnimatedPlayButton()
}