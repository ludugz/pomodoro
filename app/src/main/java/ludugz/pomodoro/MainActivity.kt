package ludugz.pomodoro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ludugz.pomodoro.ui.navigation.BottomNavigationBar
import ludugz.pomodoro.ui.navigation.Screen
import ludugz.pomodoro.ui.pages.setting.SettingPage
import ludugz.pomodoro.ui.pages.SplashPage
import ludugz.pomodoro.ui.pages.StatisticPage
import ludugz.pomodoro.ui.pages.TimerPage
import ludugz.pomodoro.ui.pages.setting.BackgroundPage
import ludugz.pomodoro.ui.theme.RockTheme

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
                            items = listOf(Screen.Timer, Screen.Statistic, Screen.Setting)
                        )
                    }
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.SPLASH_SCREEN_ROUTE,
                        modifier = Modifier.padding(top = paddingValues.calculateTopPadding()),
                    ) {
                        composable(Screen.SPLASH_SCREEN_ROUTE) {
                            SplashPage(navController = navController)
                        }
                        composable(Screen.TIMER_SCREEN_ROUTE) {
                            TimerPage(navController = navController)
                        }
                        composable(Screen.STATISTIC_SCREEN_ROUTE) {
                            StatisticPage(navController = navController)
                        }
                        composable(Screen.SETTING_SCREEN_ROUTE) {
                            SettingPage(navController = navController)
                        }
                        composable(Screen.BACKGROUND_SCREEN_ROUTE + "/{color}") {
                            BackgroundPage(navController = navController)
                        }
                    }
                }
            }
        }
    }
}