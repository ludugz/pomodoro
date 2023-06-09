package ludugz.pomodoro.ui.components

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by Tan N. Truong, on 08 June, 2023
 * Email: ludugz@gmail.com
 */

@Composable
fun Timer(
    modifier: Modifier = Modifier,
    textSize: TextUnit = 24.sp,
    color: Color = Color.White,
    isPlaying: Boolean = false,
) {
    var timeLeft by remember { mutableStateOf(POMODORO_DURATION) }
    var isRunning by remember { mutableStateOf(false) }
    val countDownTimer by remember {
        mutableStateOf(
            object : CountDownTimer(10000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    timeLeft = millisUntilFinished
                    Timber.d("timeLeft: $millisUntilFinished")
                }

        override fun onFinish() {
                    isRunning = false
                }
            })
    }

    LaunchedEffect(key1 = isPlaying) {
        if (isPlaying && !isRunning) {
            isRunning = true
            countDownTimer.start()
        } else {
            isRunning = false
            countDownTimer.cancel()
        }
    }
    Text(
        modifier = modifier,
        text = timeLeft.formattedTime(),
        fontSize = textSize,
        color = color,
    )
}

@Preview(name = "Timer", showBackground = false, widthDp = 100, heightDp = 50)
@Composable
fun PreviewTimer() {
    Timer()
}
