package ludugz.pomodoro.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ludugz.pomodoro.ui.helpers.HabitDotLevel
import ludugz.pomodoro.ui.theme.Green200
import ludugz.pomodoro.ui.theme.Green400
import ludugz.pomodoro.ui.theme.Green600
import ludugz.pomodoro.ui.theme.Green800
import timber.log.Timber
import java.time.LocalDate

/**
 * Created by Tan N. Truong, on 20 August, 2024
 * Email: ludugz@gmail.com
 */
data class Dot(
    val date: LocalDate,
    val level: HabitDotLevel,
)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DotGraph(
    modifier: Modifier = Modifier,
    dots: List<Dot> = emptyList(),
) {
    val rows = 7 // Set a fixed number of rows to ensure horizontal scrolling

    Timber.d("DotGraph Composable")
    LazyHorizontalGrid(
        rows = GridCells.Fixed(rows),
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        items(dots.size) { index ->
            val dot = dots[index]
            val color = when (dot.level) {
                HabitDotLevel.NONE -> Gray
                HabitDotLevel.WEAK -> Green200
                HabitDotLevel.NORMAL -> Green400
                HabitDotLevel.STRONG -> Green600
                HabitDotLevel.SUPER -> Green800
            }
            Box(
                modifier = Modifier
                    .width(16.dp)
                    .height(16.dp)
                    .padding(2.dp)
                    .testTag("DotGraph")
                    .clip(shape = RoundedCornerShape(4.dp))
                    .background(color = color)
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(
    showBackground = true,
)
@Composable
fun PreviewDotGraph() {
    DotGraph(
        dots = listOf(
            Dot(date = LocalDate.now(), HabitDotLevel.WEAK),
            Dot(date = LocalDate.now(), HabitDotLevel.NORMAL),
            Dot(date = LocalDate.now(), HabitDotLevel.STRONG),
            Dot(date = LocalDate.now(), HabitDotLevel.NONE),
            Dot(date = LocalDate.now(), HabitDotLevel.SUPER),
            Dot(date = LocalDate.now(), HabitDotLevel.STRONG),
            Dot(date = LocalDate.now(), HabitDotLevel.SUPER),
            Dot(date = LocalDate.now(), HabitDotLevel.SUPER),
            Dot(date = LocalDate.now(), HabitDotLevel.STRONG),
            Dot(date = LocalDate.now(), HabitDotLevel.STRONG),
            Dot(date = LocalDate.now(), HabitDotLevel.NONE),
        )
    )
}