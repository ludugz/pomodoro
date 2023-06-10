package ludugz.pomodoro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ludugz.pomodoro.ui.components.Clock
import ludugz.pomodoro.ui.components.Wave
import ludugz.pomodoro.ui.theme.Lima300
import ludugz.pomodoro.ui.theme.PomodoroTheme

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
        Box(modifier = Modifier.fillMaxSize()) {
            Clock(
                modifier = Modifier.align(alignment = Alignment.Center)
            )
            Wave(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.BottomCenter),
                color = Color.Cyan
            )
        }
    }
}

@Preview
@Composable
fun PreviewPomodoroPage() {
    PomodoroPage()
}
