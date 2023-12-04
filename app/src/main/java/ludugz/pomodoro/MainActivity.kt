package ludugz.pomodoro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import ludugz.pomodoro.ui.components.Clock
import ludugz.pomodoro.ui.helpers.Constants.CHEERING_DIALOG_CONFIRM_BUTTON
import ludugz.pomodoro.ui.helpers.Constants.CIRCLE_RADIUS
import ludugz.pomodoro.ui.helpers.Constants.EDGE_BAR_MAXIMUM_COUNT
import ludugz.pomodoro.ui.helpers.Constants.MOTIVATION_QUOTE
import ludugz.pomodoro.ui.helpers.Constants.TAP_SCREEN_TEXT
import ludugz.pomodoro.ui.helpers.pixelsToDp
import ludugz.pomodoro.ui.navigation.ScreenRoute
import ludugz.pomodoro.ui.theme.PomodoroTheme
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PomodoroTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = ScreenRoute.SPLASH.name,
                ) {
                    composable(ScreenRoute.SPLASH.name) {
//                        SplashPage(navController = navController)
                        TimerPage()
                    }
                    composable("timer") {
                        TimerPage()
                    }
                }
            }
        }
    }
}

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
                    if (edgeBarCount < EDGE_BAR_MAXIMUM_COUNT) {
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

            // Edge bar component
            Timber.d("parentHeight: ${parentHeightInDp.value}")
            Timber.d("Edge Bar count: $edgeBarCount")
            Box(
                modifier = Modifier
                    .height(currentEdgeBarHeightInDp())
                    .width(12.dp)
                    .align(alignment = Alignment.BottomEnd)
                    .background(color = Color.LightGray)
            )

            // Cheering Dialog component
            if (edgeBarCount >= EDGE_BAR_MAXIMUM_COUNT) {
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

/**
 * Cheering Dialog component
 */
@Composable
fun CheeringDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    painter: Painter,
    imageDescription: String,
) {
    val cheeringWords = LocalContext.current.resources.getStringArray(R.array.cheering_words_array)
    val randomIndex = (cheeringWords.indices).random()
    val randomCheeringWord = cheeringWords[randomIndex]

    Dialog(onDismissRequest = { onDismissRequest() }) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painter,
                    contentDescription = imageDescription,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(160.dp)
                )
                Text(
                    text = randomCheeringWord,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodySmall,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    TextButton(
                        onClick = { onConfirmation() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text(text = CHEERING_DIALOG_CONFIRM_BUTTON)
                    }
                }
            }
        }
    }
}

private fun currentEdgeBarHeightInDp(): Dp {
    Timber.d("Current edge bar height: ${(parentHeightInDp * edgeBarCount / EDGE_BAR_MAXIMUM_COUNT)}")
    return parentHeightInDp * edgeBarCount / EDGE_BAR_MAXIMUM_COUNT
}

private fun updateEdgeBarCount(): Int {
    return edgeBarCount++
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
