package ludugz.pomodoro.ui.pages.setting

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ludugz.pomodoro.ui.components.BackgroundGrid
import ludugz.pomodoro.ui.helpers.SettingsLabelTextStyle

/**
 * Created by Tan N. Truong, on 04 January, 2024
 * Email: ludugz@gmail.com
 */
@Composable
fun BackgroundPage(
    colorResource: Int,
) {
    SettingsLabelTextStyle(text = "Display")
    BackgroundGrid(
        color = Color(color = colorResource),
        itemSize = 32.dp
    )
}