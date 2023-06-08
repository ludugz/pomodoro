package ludugz.pomodoro.ui.components

import android.os.CountDownTimer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

/**
 * Created by Tan N. Truong, on 08 June, 2023
 * Email: ludugz@gmail.com
 */
@Composable
fun Timer(
    modifier: Modifier = Modifier,
    textSize: TextUnit = 24.sp,
    color: Color = Color.White
) {
    var timeLeft: Long by remember { mutableStateOf(25 * 60 * 1000) }
    var isRunning by remember { mutableStateOf(false) }
    val formattedTime =
        String.format("%02d:%02d", timeLeft.timeInMinutes(), timeLeft.timeInSeconds())

    LaunchedEffect(key1 = Unit) {
        launch {
            object : CountDownTimer(timeLeft, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    timeLeft = millisUntilFinished
                }

                override fun onFinish() {
                    isRunning = false
                }
            }.start()
        }
    }
    Text(
        modifier = modifier,
        text = formattedTime,
        fontSize = textSize,
        color = color,
    )
}

fun Long.timeInMinutes(): Long {
    return ((this / (1000 * 60)) % 60)
}

fun Long.timeInSeconds(): Long {
    return (this / 1000) % 60
}

@Preview(name = "Timer", showBackground = false, widthDp = 100, heightDp = 50)
@Composable
fun PreviewTimer() {
    Timer()
}
