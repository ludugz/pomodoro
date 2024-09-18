package ludugz.pomodoro.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ludugz.pomodoro.R
import ludugz.pomodoro.ui.helpers.Constants.POMODORO_TIMER_DURATION
import ludugz.pomodoro.ui.helpers.formattedTime
import timber.log.Timber


/**
 * Created by Tan N. Truong, on 22 May, 2024
 * Email: ludugz@gmail.com
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FocusZone(
    timeLeft: Long,
    backgroundColor: Color = Color.Black,
    onLongClick: () -> Unit = {},
    onCloseClick: () -> Unit = {},
) {
    Timber.i("FocusZone Composable")
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor)
            .combinedClickable(
                onClick = {},
                onLongClick = onLongClick
            )
    ) {
        Image(
            modifier = Modifier
                .align(alignment = Alignment.TopEnd)
                .padding(all = 16.dp)
                .clickable(onClick = onCloseClick),
            painter = painterResource(id = R.drawable.ic_close_filled_48),
            contentDescription = ""
        )
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
    FocusZone(
        timeLeft = POMODORO_TIMER_DURATION,
    )
}