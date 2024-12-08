package ludugz.pomodoro.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ludugz.pomodoro.ui.helpers.HabitDotLevel
import ludugz.pomodoro.ui.theme.Green200
import ludugz.pomodoro.ui.theme.Green400
import ludugz.pomodoro.ui.theme.Green600
import ludugz.pomodoro.ui.theme.Green800
import ludugz.pomodoro.ui.theme.LightGray
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

@OptIn(ExperimentalLayoutApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DotGraph(
    dots: List<Dot> = emptyList(),
) {
    val rows = 7 // Set a fixed number of rows to ensure horizontal scrolling

    Timber.d("DotGraph Composable")
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(2.dp), // Space between items in the same row
        verticalArrangement = Arrangement.spacedBy(2.dp),   // Space between rows
        maxItemsInEachRow = (dots.size / rows) + 1 // Control number of items in each row
    ) {
        dots.forEach { dot ->
            val color = when (dot.level) {
                HabitDotLevel.NONE -> LightGray
                HabitDotLevel.WEAK -> Green200
                HabitDotLevel.NORMAL -> Green400
                HabitDotLevel.STRONG -> Green600
                HabitDotLevel.SUPER -> Green800
            }
            Box(
                modifier = Modifier
                    .size(16.dp) // Fixed size for each dot
                    .clip(RoundedCornerShape(4.dp))
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
            Dot(date = LocalDate.now(), HabitDotLevel.STRONG),
            Dot(date = LocalDate.now(), HabitDotLevel.STRONG),
            Dot(date = LocalDate.now(), HabitDotLevel.WEAK),
            Dot(date = LocalDate.now(), HabitDotLevel.WEAK),
            Dot(date = LocalDate.now(), HabitDotLevel.NORMAL),
            Dot(date = LocalDate.now(), HabitDotLevel.STRONG),
            Dot(date = LocalDate.now(), HabitDotLevel.WEAK),
            Dot(date = LocalDate.now(), HabitDotLevel.WEAK),
            Dot(date = LocalDate.now(), HabitDotLevel.NORMAL),
            Dot(date = LocalDate.now(), HabitDotLevel.NORMAL),
            Dot(date = LocalDate.now(), HabitDotLevel.STRONG),
        )
    )
}