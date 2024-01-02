package ludugz.pomodoro.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Created by Tan N. Truong, on 01 January, 2024
 * Email: ludugz@gmail.com
 */
@Composable
fun BackgroundGrid(color: Color, itemSize: Dp) {
    Column {
        repeat(3) { row ->
            Row {
                repeat(6) { column ->
                    // Place your content here
                    Box(
                        modifier = Modifier
                            .padding(5.dp)
                            .background(color = color)
                            .size(size = itemSize)
                    )
                }
            }
        }
    }
}
