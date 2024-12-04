package ludugz.pomodoro

import android.Manifest
import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeRight
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.GrantPermissionRule
import ludugz.pomodoro.ui.components.Dot
import ludugz.pomodoro.ui.components.DotGraph
import ludugz.pomodoro.ui.helpers.HabitDotLevel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate

/**
 * Created by Tan N. Truong, on 29 November, 2024
 * Email: ludugz@gmail.com
 */
@RunWith(AndroidJUnit4::class)
class DotGraphScrollBenchmark {

    @get:Rule
    var permissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun benchmarkDotGraphScroll() {
        val dots = List(size = 1000) { index ->
            Dot(
                date = LocalDate.now().plusDays(index.toLong()),
                level = HabitDotLevel.values().random()
            )
        }
        composeTestRule.setContent {
            DotGraph(dots = dots)
        }

        benchmarkRule.measureRepeated {
            composeTestRule.onNodeWithTag("DotGraph").performTouchInput {
                swipeRight(startX = 0f, endX = 100f)
            }
        }
    }
}