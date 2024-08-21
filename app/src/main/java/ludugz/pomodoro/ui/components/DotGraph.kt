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
import ludugz.pomodoro.ui.theme.Lima200
import ludugz.pomodoro.ui.theme.Lima300
import java.time.LocalDate
import java.time.Year


/**
 * Created by Tan N. Truong, on 20 August, 2024
 * Email: ludugz@gmail.com
 */
data class Dot(val date: LocalDate, val isActive: Boolean)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DotGraph() {
    val dots = generateDotsForYear(Year.now().value)
    val rows = 7 // Set a fixed number of rows to ensure horizontal scrolling

    LazyHorizontalGrid(
        rows = GridCells.Fixed(rows),
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        items(dots.size) { index ->
            val dot = dots[index]
            Box(
                modifier = Modifier
                    .padding(1.dp)
                    .width(16.dp)
                    .height(16.dp)
                    .background(if (dot.isActive) Lima300 else Lima200)
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
        days.add(Dot(currentDate, isActive = (0..1).random() == 1))
        currentDate = currentDate.plusDays(1)
    }
    return days
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(widthDp = 720, heightDp = 1080, showBackground = true)
@Composable
fun PreviewDotGraph() {
    DotGraph()
}