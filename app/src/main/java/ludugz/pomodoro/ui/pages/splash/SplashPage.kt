package ludugz.pomodoro.ui.pages.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import ludugz.pomodoro.R
import ludugz.pomodoro.ui.navigation.Screen


/**
 * Created by Tan N. Truong, on 26 December, 2023
 * Email: ludugz@gmail.com
 */

@Composable
fun SplashPage(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(),
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.rock_garden),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(320.dp)
        )
    }

    LaunchedEffect(key1 = true) {
        delay(1000)
        navController.navigate(Screen.TIMER_SCREEN_ROUTE)
    }
}

@Preview
@Composable
fun PreviewSplashPage() {
    SplashPage()
}

