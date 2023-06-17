package ludugz.pomodoro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import ludugz.pomodoro.ui.components.Clock
import ludugz.pomodoro.ui.components.Timer
import ludugz.pomodoro.ui.components.Wave
import ludugz.pomodoro.ui.theme.Lima100
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

@Composable
fun PomodoroPage() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Lima300
    ) {
        var colorWave by remember { mutableStateOf(Color.White) }
        var boxHeight by remember { mutableStateOf(0.dp) }
        var isClockClicked by remember { mutableStateOf(true) }
        val shouldShowWaveAnimation = boxHeight > 0.dp && isClockClicked
        
        Box(modifier = Modifier
            .fillMaxSize()
            .onGloballyPositioned { coordinates ->
                boxHeight = coordinates.size.height.dp
            }) {
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
                    onClick = {
                        isClockClicked = !isClockClicked
                    }
                )
                Timer(
                    modifier = Modifier
                        .padding(top = 16.dp),
                    color = Color.White
                )
            }

            if (shouldShowWaveAnimation) {
                Wave(
                    modifier = Modifier
                        .fillMaxWidth()
                        .zIndex(zIndex = 1f)
                        .align(alignment = Alignment.BottomCenter),
                    color = Rock300,
                    targetHeight = boxHeight
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewPomodoroPage() {
    PomodoroPage()
}
