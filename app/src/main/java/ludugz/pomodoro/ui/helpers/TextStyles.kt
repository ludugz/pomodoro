package ludugz.pomodoro.ui.helpers

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ludugz.pomodoro.ui.theme.RockTypography


/**
 * Created by Tan N. Truong, on 19 December, 2023
 * Email: ludugz@gmail.com
 */

/**
 * Setting screen
 */
@Composable
fun SettingsLabelTextStyle(text: String) = Text(
    text = text,
    style = RockTypography.titleSmall,
)
