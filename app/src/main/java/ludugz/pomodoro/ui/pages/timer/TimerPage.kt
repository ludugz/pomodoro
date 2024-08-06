package ludugz.pomodoro.ui.pages.timer

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import ludugz.pomodoro.R
import ludugz.pomodoro.domain.TimerPageViewModel
import ludugz.pomodoro.ui.components.CheeringDialog
import ludugz.pomodoro.ui.components.Clock
import ludugz.pomodoro.ui.components.Counter
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

private var displayDialogCount by mutableIntStateOf(0)
private var isTimerRunning by mutableStateOf(false)
private var isTimerReset by mutableStateOf(false)
private var parentHeightInDp by mutableStateOf(0.dp)
private var timeLeft by mutableLongStateOf(Constants.POMODORO_TIMER_DURATION)
private var uiState by mutableStateOf(UIState())
private var timerClockCounter by mutableIntStateOf(0)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TimerPage(
    navController: NavController = rememberNavController(),
    viewModel: TimerPageViewModel = hiltViewModel(),
) {
    val animatedBackgroundColor by animateColorAsState(
        if (uiState == UIState.FocusZone) {
            Color.Black
        } else {
            Color.White
        },
        animationSpec = tween(durationMillis = 1000),
        label = "color"
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = animatedBackgroundColor,
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

        AnimatedContent(
            targetState = uiState,
            transitionSpec = {
                fadeIn(animationSpec = tween(1000)) togetherWith fadeOut(animationSpec = tween(1000))
            },
            label = "",
        ) { targetState ->
            if (targetState == UIState.FocusZone) {
                FocusZone(
                    timeLeft = timeLeft,
                    backgroundColor = animatedBackgroundColor,
                    onLongClick = {
                        uiState = UIState.TimerPage
                    },
                    onCloseClick = {
                        uiState = UIState.TimerPage
                    }
                )
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .onGloballyPositioned { coordinates ->
                            parentHeightInDp = coordinates.size.height.pixelsToDp()
                        }
                        .combinedClickable(onClick = {
                            if (displayDialogCount < Constants.SHOULD_DISPLAY_CHEERING_DIALOG_MAXIMUM_COUNT) {
                                displayDialogCount++
                            }
                            timerClockCounter++
                        }, onLongClick = {
                            uiState = UIState.FocusZone
                        }),
                ) {
                    // Motivation Quote component
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                        text = Constants.TAP_ME_TITLE,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Counter(
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally)
                            .padding(
                                top = 16.dp
                            ),
                        counter = timerClockCounter
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

                        // Play/Pause button
                        RoundedButton(
                            modifier = Modifier.padding(all = 6.dp),
                            resource = pauseButtonResource,
                        ) {
                            isTimerRunning = !isTimerRunning
                            isTimerReset = false
                        }

                        // Reset Button
                        RoundedButton(
                            modifier = Modifier.padding(all = 6.dp),
                            resource = R.drawable.ic_reset_transparent_24,
                        ) {
                            isTimerReset = true
                            isTimerRunning = false
                            timerClockCounter = 0
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
                    if (displayDialogCount >= Constants.SHOULD_DISPLAY_CHEERING_DIALOG_MAXIMUM_COUNT) {
                        CheeringDialog(
                            onDismissRequest = { displayDialogCount = 0 },
                            onConfirmation = {},
                        )
                    }
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
