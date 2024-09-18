package ludugz.pomodoro.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ludugz.pomodoro.ui.helpers.Constants.CIRCLE_RADIUS
import ludugz.pomodoro.ui.helpers.Constants.CIRCLE_THICKNESS
import ludugz.pomodoro.ui.helpers.Constants.POMODORO_TIMER_DURATION
import ludugz.pomodoro.ui.helpers.formattedTime
import timber.log.Timber

/**
 * Created by Tan N. Truong, on 23 May, 2023
 * Email: ludugz@gmail.com
 */
@Composable
fun Clock(
    modifier: Modifier = Modifier,
    timeLeft: Long = POMODORO_TIMER_DURATION,
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
        )

        Timer(
            timeLeft = timeLeft,
        )
    }
}

@Composable
fun Timer(
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    timeLeft: Long = POMODORO_TIMER_DURATION,
) {

    Text(
        modifier = modifier,
        text = timeLeft.formattedTime(),
        style = MaterialTheme.typography.titleLarge,
        color = color,
    )
}

@Composable
fun BorderOuterCircle(
    modifier: Modifier = Modifier,
    color: Color = Color.LightGray,
    circleSize: Dp = CIRCLE_RADIUS.dp,
    thickness: Dp = CIRCLE_THICKNESS.dp,
) {
    val strokeWidth = with(LocalDensity.current) { thickness.toPx() }
    Canvas(modifier = modifier) {
        drawCircle(
            color = color,
            radius = size.minDimension / 2,
            center = Offset(size.width / 2, size.height / 2),
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
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

@Preview(name = "Timer", showBackground = false, widthDp = 100, heightDp = 50)
@Composable
fun PreviewTimer() {
    Timer()
}
