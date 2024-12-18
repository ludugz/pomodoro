package ludugz.pomodoro.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ludugz.pomodoro.ui.theme.BackgroundColorsMap
import timber.log.Timber

/**
 * Created by Tan N. Truong, on 01 January, 2024
 * Email: ludugz@gmail.com
 */

@Composable
fun ColorsGrid(
    modifier: Modifier = Modifier,
    itemPadding: Dp,
    itemSize: Dp,
    chunkedColumn: Int,
) {
    val colors = BackgroundColorsMap
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        colors.entries.chunked(size = chunkedColumn).forEach { chunk ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                chunk.forEach { colorEntry ->
                    Timber.d("colorEntry: $colorEntry")
                    Box(
                        modifier = Modifier
                            .padding(all = itemPadding)
                            .size(size = itemSize)
                            .clip(shape = RoundedCornerShape(size = 4.dp))
                            .background(color = Color(color = colorEntry.value))
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ColorsGridPreview() {
    ColorsGrid(
        itemPadding = 2.dp,
        itemSize = 32.dp,
        chunkedColumn = 5,
    )
}
