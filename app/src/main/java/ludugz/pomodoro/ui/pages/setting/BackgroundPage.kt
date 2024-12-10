package ludugz.pomodoro.ui.pages.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ludugz.pomodoro.ui.components.ColorsGrid
import ludugz.pomodoro.ui.helpers.BackgroundLabelTextStyle

/**
 * Created by Tan N. Truong, on 04 January, 2024
 * Email: ludugz@gmail.com
 */
@Composable
fun BackgroundPage(
    modifier: Modifier = Modifier,
    colorResource: Long,
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BackgroundLabelTextStyle(text = "Color")
        ColorsGrid(
            itemPadding = 2.dp,
            itemSize = 32.dp,
            chunkedColumn = 5,
        )
    }
}