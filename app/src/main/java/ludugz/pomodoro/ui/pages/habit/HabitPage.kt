package ludugz.pomodoro.ui.pages.habit

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ludugz.pomodoro.ui.components.Dot
import ludugz.pomodoro.ui.components.DotGraph
import ludugz.pomodoro.ui.components.HabitItemCardEditLayout
import ludugz.pomodoro.ui.components.RoundedIcon
import ludugz.pomodoro.ui.helpers.HabitDotLevel
import ludugz.pomodoro.ui.theme.MonospaceTypography
import timber.log.Timber
import java.time.LocalDate
import java.time.Year
import kotlin.random.Random

/**
 * Note: each session is defined as a habit
 *
 * Created by Tan N. Truong, on 26 December, 2023
 * Email: ludugz@gmail.com
 */

var showEditHabit by mutableStateOf(false)
var sessionCount by mutableIntStateOf(0)
var selectedHabit = 0

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HabitPage(navController: NavController) {
    val scope = rememberCoroutineScope()
    val habitItemList =
        remember { mutableStateListOf(HabitItemInfo(emptyList(), "Default Session")) }

    LaunchedEffect(key1 = Unit) {
        scope.launch {
            withContext(context = Dispatchers.IO) {
                Timber.d("Launched Effect Thread: ${Thread.currentThread().name}")
                val dots = generateDotsForYear(Year.now().value)
                habitItemList.forEachIndexed { index, item ->
                    habitItemList[index] = item.copy(dots = dots)
                }
            }
        }
    }

    Timber.d("Compose Thread: ${Thread.currentThread().name}")
    LazyColumn(modifier = Modifier) {
        items(count = habitItemList.size) { index: Int ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .combinedClickable(onClick = {}, onLongClick = {
                        showEditHabit = true
                        selectedHabit = index
                    }),
                shape = RoundedCornerShape(size = 16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = habitItemList[index].name,
                        style = MonospaceTypography.labelMedium,
                    )
                    RoundedIcon(
                        imageVector = Icons.Default.MoreVert, contentDescription = "Check Icon"
                    ) {
                        showEditHabit = true
                        selectedHabit = index
                    }
                }
                if (habitItemList[index].dots.isNotEmpty()) {
                    DotGraph(
                        dots = habitItemList[index].dots
                    )
                }
            }
        }
    }
    AnimatedVisibility(
        visible = showEditHabit, enter = expandVertically(), exit = shrinkVertically()
    ) {
        HabitItemCardEditLayout(name = habitItemList[selectedHabit].name, onDismissRequest = {
            showEditHabit = false
        }, onConfirmation = { sessionName ->
            showEditHabit = false
            habitItemList[selectedHabit].name = sessionName
        })
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun generateDotsForYear(year: Int): List<Dot> {
    Timber.d("Start generate Dots")
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
    Timber.d("Finish generate Dots")
    return days
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(widthDp = 360, heightDp = 360, showBackground = true)
@Composable
fun PreviewHabitPage() {
    HabitPage(navController = rememberNavController())
}