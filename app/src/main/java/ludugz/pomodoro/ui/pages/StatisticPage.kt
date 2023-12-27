package ludugz.pomodoro.ui.pages

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


/**
 * Created by Tan N. Truong, on 26 December, 2023
 * Email: ludugz@gmail.com
 */

@Composable
fun StatisticPage(navController: NavController) {
    Text(text = "Statistic Page")
}

@Preview
@Composable
fun PreviewStatisticPage() {
    StatisticPage(navController = rememberNavController())
}