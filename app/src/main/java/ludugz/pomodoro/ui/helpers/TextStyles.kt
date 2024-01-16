package ludugz.pomodoro.ui.helpers

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ludugz.pomodoro.ui.theme.RockTypography


/**
 * Created by Tan N. Truong, on 19 December, 2023
 * Email: ludugz@gmail.com
 */

/**
 * Setting screen
 */
@Composable
fun SettingsTitleTextStyle(text: String) = Text(
    text = text,
    style = RockTypography.titleSmall,
)

@Composable
fun SettingsSubtitleTextStyle(modifier: Modifier = Modifier, text: String) = Text(
    modifier = modifier,
    text = text,
    style = RockTypography.bodyMedium,
)

/**
 * Background screen
 */
@Composable
fun BackgroundLabelTextStyle(text: String) = Text(
    text = text,
    style = RockTypography.titleMedium,
)
