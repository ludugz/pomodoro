package ludugz.pomodoro.ui.helpers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


/**
 * Created by Tan N. Truong, on 19 December, 2023
 * Email: ludugz@gmail.com
 */

@Composable
fun SettingsLabelTextStyle(text: String) = Text(
    text = text,
    modifier = Modifier
        .background(Color.Black)
        .padding(16.dp),
    style = MaterialTheme.typography.bodyMedium,
)
