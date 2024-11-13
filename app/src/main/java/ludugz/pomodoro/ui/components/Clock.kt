package ludugz.pomodoro.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ludugz.pomodoro.ui.helpers.Constants.CIRCLE_RADIUS
import ludugz.pomodoro.ui.helpers.Constants.CIRCLE_THICKNESS
import ludugz.pomodoro.ui.helpers.Constants.POMODORO_TIMER_DURATION_MINUTES
import ludugz.pomodoro.ui.helpers.formattedTime
import ludugz.pomodoro.ui.theme.LightColorScheme
import timber.log.Timber

/**
 * Created by Tan N. Truong, on 23 May, 2023
 * Email: ludugz@gmail.com
 */
@Composable
fun Clock(
    modifier: Modifier = Modifier,
    timeLeft: Long = POMODORO_TIMER_DURATION_MINUTES,
    onPlayOrPause: () -> Unit = {},
) {
    Timber.i("Clock Composable")
    Box(
        modifier = modifier
            .clip(shape = CircleShape)
            .clickable { onPlayOrPause() },
        contentAlignment = Alignment.Center
    ) {
        BorderOuterCircle(
            modifier = modifier.fillMaxSize(),
            timeLeft = timeLeft,
        )

        Timer(
            timeLeft = timeLeft,
        )
    }
}

@Composable
fun Timer(
    modifier: Modifier = Modifier,
    timeLeft: Long = POMODORO_TIMER_DURATION_MINUTES,
) {

    Text(
        modifier = modifier,
        text = timeLeft.formattedTime(),
        style = MaterialTheme.typography.titleLarge,
    )
}

@Composable
fun BorderOuterCircle(
    modifier: Modifier = Modifier,
    passedColor: Color = LightColorScheme.primary,
    remainingColor: Color = Color.White,
    circleSize: Dp = CIRCLE_RADIUS.dp,
    thickness: Dp = CIRCLE_THICKNESS.dp,
    timeLeft: Long = POMODORO_TIMER_DURATION_MINUTES,
) {
    val strokeWidth = with(LocalDensity.current) { thickness.toPx() }
    Canvas(modifier = modifier) {
        val angle =
            ((POMODORO_TIMER_DURATION_MINUTES - timeLeft) * 360 / POMODORO_TIMER_DURATION_MINUTES).toFloat()

        drawArc(
            color = passedColor,
            startAngle = -90f,
            sweepAngle = angle,
            useCenter = false,
            style = Stroke(width = strokeWidth)
        )

        drawArc(
            color = remainingColor,
            startAngle = angle - 90,
            sweepAngle = 360f - angle,
            useCenter = false,
            style = Stroke(width = strokeWidth)
        )
    }
}

@Preview(
    name = "Clock",
    backgroundColor = 0xFFFFFFFF,
    showBackground = true,
    widthDp = 150,
    heightDp = 150
)

@Composable
fun PreviewClock() {
    Clock()
}

@Preview(name = "Timer", widthDp = 100, heightDp = 50, showBackground = true)
@Composable
fun PreviewTimer() {
    Timer()
}

@Preview(widthDp = 200, heightDp = 200)
@Composable
fun PreviewBorderOuterCircle() {
    BorderOuterCircle(modifier = Modifier.size(200.dp))
}
