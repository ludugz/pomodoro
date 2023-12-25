package ludugz.pomodoro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import ludugz.pomodoro.ui.components.Clock
import ludugz.pomodoro.ui.components.Timer
import ludugz.pomodoro.ui.components.Wave
import ludugz.pomodoro.ui.theme.Lima300
import ludugz.pomodoro.ui.theme.PomodoroTheme
import ludugz.pomodoro.ui.theme.Rock300

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PomodoroTheme {
                PomodoroPage()
            }
        }
    }
}

/**
 * Splash Page component
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
        delay(3000)
        navController.navigate(ScreenRoute.TIMER.name)
    }
}

var edgeBarCount by mutableIntStateOf(0)
var shouldPlay by mutableStateOf(false)
var parentHeightInDp by mutableStateOf(0.dp)

/**
 * Timer Page component
 */
@Composable
fun TimerPage() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White,
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .onGloballyPositioned { coordinates ->
                    parentHeightInDp = coordinates.size.height.pixelsToDp()
                }
                .clickable {
                    if (edgeBarCount < SHOULD_DISPLAY_CHEERING_DIALOG_MAXIMUM_COUNT) {
                        edgeBarCount++
                    }
                },
        ) {
            // Motivation Quote component
            Text(
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .padding(top = 128.dp),
                text = MOTIVATION_QUOTE,
                style = MaterialTheme.typography.bodyMedium,
            )

            // Timer Clock compoent
            Clock(
                modifier = Modifier
                    .size(CIRCLE_RADIUS.dp)
                    .align(alignment = Alignment.Center),
                shouldPlay = shouldPlay
            )

            // Tap Screen component
            Text(
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .padding(
                        bottom = 36.dp
                    ),
                text = TAP_SCREEN_TEXT,
                style = MaterialTheme.typography.bodySmall,
            )

            // Cheering Dialog component
            if (edgeBarCount >= SHOULD_DISPLAY_CHEERING_DIALOG_MAXIMUM_COUNT) {
                CheeringDialog(
                    onDismissRequest = { edgeBarCount = 0 },
                    onConfirmation = {},
                    painter = painterResource(id = R.drawable.smiling_dog),
                    imageDescription = "Smiling dog",
                )
            }
        }
    }
}

            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .align(alignment = Alignment.Center)
                    .fillMaxHeight()
                    .zIndex(zIndex = 2f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Clock(
                    color = Color.White,
                    isPlaying = isPlaying,
                    isFirstTime = isFirstTime
                ) {
                    isPlaying = !isPlaying
                    isFirstTime = false
                }
                Timer(
                    modifier = Modifier
                        .padding(top = 16.dp),
                    color = Color.White,
                    isPlaying = isPlaying
                )
            }

            if (!isFirstTime) {
                Wave(
                    modifier = Modifier
                        .fillMaxWidth()
                        .zIndex(zIndex = 1f)
                        .align(alignment = Alignment.BottomCenter),
                    color = Rock300,
                    initialValue = if (isPlaying) 0.dp else maxHeight,
                    targetValue = if (isPlaying) maxHeight else 0.dp,
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewSplashPage() {
    SplashPage()
}

@Preview
@Composable
fun PreviewPomodoroPage() {
    TimerPage()
}
