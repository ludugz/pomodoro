package ludugz.pomodoro.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import ludugz.pomodoro.ui.helpers.formattedTime


/**
 * Created by Tan N. Truong, on 22 May, 2024
 * Email: ludugz@gmail.com
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FocusZone(
    timeLeft: Long,
    onLongClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .combinedClickable(
                onClick = {},
                onLongClick = onLongClick
            )
    ) {
        Text(
            modifier = Modifier
                .align(alignment = Alignment.Center),
            text = timeLeft.formattedTime(),
            fontSize = 32.sp,
            color = Color.White,
        )
    }
}

@Composable
@Preview
fun PreviewFocusZone() {
//    FocusZone()
}