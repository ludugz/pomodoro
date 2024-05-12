package ludugz.pomodoro.ui.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import ludugz.pomodoro.R
import ludugz.pomodoro.ui.components.CheeringDialog
import ludugz.pomodoro.ui.components.Clock
import ludugz.pomodoro.ui.helpers.Constants
import ludugz.pomodoro.ui.helpers.pixelsToDp


/**
 * Created by Tan N. Truong, on 26 December, 2023
 * Email: ludugz@gmail.com
 */

/**
 * Timer Page component
 */

var edgeBarCount by mutableIntStateOf(0)
var shouldPlay by mutableStateOf(false)
var parentHeightInDp by mutableStateOf(0.dp)

@Composable
fun TimerPage(navController: NavController = rememberNavController()) {
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
                    if (edgeBarCount < Constants.SHOULD_DISPLAY_CHEERING_DIALOG_MAXIMUM_COUNT) {
                        edgeBarCount++
                    }
                },
        ) {
            // Motivation Quote component
            Text(
                modifier = Modifier
                    .align(alignment = Alignment.TopCenter)
                    .padding(top = 128.dp),
                text = Constants.MOTIVATION_QUOTE,
                style = MaterialTheme.typography.bodyMedium,
            )

            // Timer Clock component
            Clock(
                modifier = Modifier
                    .size(Constants.CIRCLE_RADIUS.dp)
                    .align(alignment = Alignment.Center), shouldPlay = shouldPlay
            )

            // Tap Screen component
            Text(
                modifier = Modifier
                    .align(
                        alignment = Alignment.BottomCenter
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

@Preview
@Composable
fun TimerPagePreview() {
    TimerPage()
}
