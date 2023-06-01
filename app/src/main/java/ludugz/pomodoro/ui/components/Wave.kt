package ludugz.pomodoro.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ludugz.pomodoro.ui.theme.Pink40

/**
 * Created by Tan N. Truong, on 01 June, 2023
 * Email: ludugz@gmail.com
 */

@Composable
fun Wave(modifier: Modifier = Modifier) {
    val strokeWidth = with(LocalDensity.current) { 4.dp.toPx() }
    Canvas(modifier = modifier) {
        drawLine(
            strokeWidth = strokeWidth,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            color = Pink40
        )
    }
}

@Preview
@Composable
fun PreviewWaveAnimation() {
    Wave(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
    )
}