package ludugz.pomodoro.ui.navigation

import androidx.annotation.DrawableRes
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

/**
 * Created by Tan N. Truong, on 16 November, 2023
 * Email: ludugz@gmail.com
 */
sealed class Screen(val route: String, @DrawableRes val icon: Int) {
    object Splash : Screen(SPLASH_SCREEN_ROUTE, UNDEFINED_DRAWABLE_RES)
    object Timer : Screen(TIMER_SCREEN_ROUTE, R.drawable.timer_nav_icon)
    object Statistic : Screen(STATISTIC_SCREEN_ROUTE, R.drawable.statistic_nav_icon)
    object Setting : Screen(SETTING_SCREEN_ROUTE, R.drawable.gear_setting_nav_icon)

    companion object {
        const val UNDEFINED_DRAWABLE_RES = -1

        const val SPLASH_SCREEN_ROUTE = "Splash"
        const val TIMER_SCREEN_ROUTE = "Timer"
        const val STATISTIC_SCREEN_ROUTE = "Statistic"
        const val SETTING_SCREEN_ROUTE = "Setting"
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
                        painter = painterResource(id = item.icon),
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
    BottomNavigationBar(
        navController = rememberNavController(),
        items = listOf(Screen.Timer, Screen.Statistic, Screen.Setting)
    )
}
