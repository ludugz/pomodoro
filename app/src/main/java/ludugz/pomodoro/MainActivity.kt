package ludugz.pomodoro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import ludugz.pomodoro.ui.navigation.BottomNavigationBar
import ludugz.pomodoro.ui.navigation.Screen
import ludugz.pomodoro.ui.pages.setting.SettingPage
import ludugz.pomodoro.ui.pages.splash.SplashPage
import ludugz.pomodoro.ui.pages.statistic.StatisticPage
import ludugz.pomodoro.ui.pages.timer.TimerPage
import ludugz.pomodoro.ui.pages.setting.BackgroundPage
import ludugz.pomodoro.ui.theme.BackgroundColorsMap
import ludugz.pomodoro.ui.theme.RockTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RockTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            navController = navController,
                            items = listOf(Screen.Timer, Screen.Habit, Screen.Setting)
                        )
                    }
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.TIMER_SCREEN_ROUTE,
                        modifier = Modifier.padding(top = paddingValues.calculateTopPadding()),
                    ) {
                        composable(route = Screen.TIMER_SCREEN_ROUTE) {
                            TimerPage(navController = navController)
                        }
                        composable(route = Screen.HABIT_SCREEN_ROUTE) {
                            HabitPage(navController = navController)
                        }
                        composable(route = Screen.SETTING_SCREEN_ROUTE, arguments = listOf()) {
                            SettingPage(navController = navController)
                        }
                        composable(
                            route = "background/{color}",
                            arguments = listOf(
                                navArgument(name = Screen.Background.colorTypeArg) {
                                    type = NavType.StringType
                                }
                            )
                        ) { navBackStackEntry ->
                            val backgroundColor =
                                navBackStackEntry.arguments?.getString(Screen.Background.colorTypeArg)
                                    ?: ""
                            BackgroundPage(
                                colorResource = BackgroundColorsMap.get(
                                    backgroundColor
                                ) ?: 0
                            )
                        }
                    }
                }
            }
        }
    }
}