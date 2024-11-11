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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ludugz.pomodoro.ui.helpers.HabitDotLevel
import ludugz.pomodoro.ui.theme.Gray
import ludugz.pomodoro.ui.theme.Green200
import ludugz.pomodoro.ui.theme.Green400
import ludugz.pomodoro.ui.theme.Green600
import ludugz.pomodoro.ui.theme.Green800
import java.time.LocalDate
import java.time.Year
import kotlin.random.Random


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
fun DotGraph(modifier: Modifier = Modifier) {
    val dots = generateDotsForYear(Year.now().value)
    val rows = 7 // Set a fixed number of rows to ensure horizontal scrolling

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
                    .padding(2.dp)
                    .width(16.dp)
                    .height(16.dp)
                    .background(color = color)
            )

        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun generateDotsForYear(year: Int): List<Dot> {
    val startDate = LocalDate.of(year, 1, 1)
    val endDate = LocalDate.of(year, 12, 31)
    val days = mutableListOf<Dot>()
    var currentDate = startDate
    while (!currentDate.isAfter(endDate)) {
        // TODO: Add random for now, need to remove later
        val values = HabitDotLevel.values()
        days.add(Dot(date = currentDate, level = values[Random.nextInt(values.size)]))
        currentDate = currentDate.plusDays(1)
    }
    return days
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(
    showBackground = true,
)
@Composable
fun PreviewDotGraph() {
    DotGraph()
}