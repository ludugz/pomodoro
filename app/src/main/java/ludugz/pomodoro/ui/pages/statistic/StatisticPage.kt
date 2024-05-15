package ludugz.pomodoro.ui.pages.statistic

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


/**
 * Created by Tan N. Truong, on 26 December, 2023
 * Email: ludugz@gmail.com
 */

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun StatisticPage(navController: NavController) {

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(widthDp = 360, heightDp = 360, showBackground = true)
@Composable
fun PreviewStatisticPage() {
    StatisticPage(navController = rememberNavController())
}