package ludugz.pomodoro.ui.pages.timer

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import ludugz.pomodoro.R
import ludugz.pomodoro.ui.components.CheeringDialog
import ludugz.pomodoro.ui.components.Clock
import ludugz.pomodoro.ui.components.FocusZone
import ludugz.pomodoro.ui.components.RoundedButton
import ludugz.pomodoro.ui.helpers.Constants
import ludugz.pomodoro.ui.helpers.pixelsToDp
import ludugz.pomodoro.ui.pages.states.UIState
import timber.log.Timber


/**
 * Created by Tan N. Truong, on 26 December, 2023
 * Email: ludugz@gmail.com
 */

/**
 * Timer Page component
 */

private var edgeBarCount by mutableIntStateOf(0)
private var isTimerRunning by mutableStateOf(false)
private var isTimerReset by mutableStateOf(false)
private var parentHeightInDp by mutableStateOf(0.dp)
private var timeLeft by mutableLongStateOf(Constants.POMODORO_TIMER_DURATION)
private var uiState by mutableStateOf(UIState())


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TimerPage(navController: NavController = rememberNavController()) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White,
    ) {

        LaunchedEffect(key1 = isTimerRunning, key2 = isTimerReset) {
            if (!isTimerReset) {
                while (timeLeft > 0 && isTimerRunning) {
                    delay(1000L)
                    timeLeft--
                    Timber.d("Timer is running: time left = $timeLeft")
                }
            } else {
                timeLeft = Constants.POMODORO_TIMER_DURATION
                Timber.d("Timer is reset: time left = $timeLeft")
            }
        }

        if (isFocusZoneVisible) {
            FocusZone(timeLeft = timeLeft) {
                isFocusZoneVisible = false
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .onGloballyPositioned { coordinates ->
                        parentHeightInDp = coordinates.size.height.pixelsToDp()
                    }
                    .combinedClickable(
                        onClick = {
                            if (edgeBarCount < Constants.SHOULD_DISPLAY_CHEERING_DIALOG_MAXIMUM_COUNT) {
                                edgeBarCount++
                            }
                        },
                        onLongClick = {
                            isFocusZoneVisible = true
                        }
                    ),
            ) {
                // Motivation Quote component
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally),
                    text = Constants.MOTIVATION_QUOTE,
                    style = MaterialTheme.typography.bodyMedium,
                )

                Spacer(modifier = Modifier.weight(1f))

                // Timer Clock component
                Clock(
                    modifier = Modifier
                        .size(Constants.CIRCLE_RADIUS.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                    timeLeft = timeLeft,
                ) {
                    // TODO: no-op for now
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(alignment = Alignment.CenterHorizontally),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    val pauseButtonResource = if (isTimerRunning) {
                        R.drawable.ic_pause_transparent_24
                    } else {
                        R.drawable.ic_play_transparent_24
                    }
                    RoundedButton(
                        modifier = Modifier.padding(all = 6.dp),
                        resource = pauseButtonResource,
                    ) {
                        isTimerRunning = !isTimerRunning
                        isTimerReset = false
                    }

                    RoundedButton(
                        modifier = Modifier.padding(all = 6.dp),
                        resource = R.drawable.ic_reset_transparent_24,
                    ) {
                        isTimerReset = true
                        isTimerRunning = false
                    }
                }

                Spacer(modifier = Modifier.weight(2f))

                // Tap Screen component
                Text(
                    modifier = Modifier
                        .align(
                            alignment = Alignment.CenterHorizontally
                        )
                        .padding(
                            bottom = parentHeightInDp / 5
                        ),
                    text = Constants.TAP_SCREEN_TEXT,
                    style = MaterialTheme.typography.bodySmall,
                )

                // Cheering Dialog component
                if (edgeBarCount >= Constants.SHOULD_DISPLAY_CHEERING_DIALOG_MAXIMUM_COUNT) {
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
}

@Preview
@Composable
fun TimerPagePreview() {
    TimerPage()
}