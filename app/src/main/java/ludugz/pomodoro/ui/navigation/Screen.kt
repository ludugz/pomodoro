package ludugz.pomodoro.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import ludugz.pomodoro.R
import ludugz.pomodoro.ui.theme.PomodoroidTheme

/**
 * Created by Tan N. Truong, on 16 November, 2023
 * Email: ludugz@gmail.com
 */
sealed class Screen(val route: String, @DrawableRes val iconRes: Int) {
    object Timer : Screen(TIMER_SCREEN_ROUTE, R.drawable.timer_nav_icon)
    object Habit : Screen(HABIT_SCREEN_ROUTE, R.drawable.habit_nav_icon)
    object Setting : Screen(SETTING_SCREEN_ROUTE, R.drawable.gear_setting_nav_icon)
    object Background : Screen(route = BACKGROUND_SCREEN_ROUTE, iconRes = UNDEFINED_DRAWABLE_RES) {
        const val colorTypeArg = "color"
        val routeWithArgs = "${route}/{${colorTypeArg}}"
    }

    companion object {
        const val UNDEFINED_DRAWABLE_RES = -1

        const val TIMER_SCREEN_ROUTE = "timer"
        const val HABIT_SCREEN_ROUTE = "habit"
        const val SETTING_SCREEN_ROUTE = "setting"
        const val BACKGROUND_SCREEN_ROUTE = "background"
    }
}

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    items: List<Screen>,
) {
    var selectedItem by remember { mutableIntStateOf(0) }
    NavigationBar(modifier = modifier) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconRes),
                        contentDescription = "Bottom Navigation Icon"
                    )
                },
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    navController.navigate(item.route)
                }
            )
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    PomodoroidTheme {
        BottomNavigationBar(
            navController = rememberNavController(),
            items = listOf(Screen.Timer, Screen.Habit, Screen.Setting)
        )
    }
}
