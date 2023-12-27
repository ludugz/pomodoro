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
fun SettingPage(navController: NavController) {
    Text(text = "Setting Page")
}

@Preview
@Composable
fun PreviewSettingPage() {
    SettingPage(navController = rememberNavController())
}