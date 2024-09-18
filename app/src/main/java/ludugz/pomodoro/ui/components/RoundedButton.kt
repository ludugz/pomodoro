package ludugz.pomodoro.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import ludugz.pomodoro.R
import ludugz.pomodoro.ui.helpers.Constants.EMPTY_STRING
import timber.log.Timber


/**
 * Created by Tan N. Truong, on 15 May, 2024
 * Email: ludugz@gmail.com
 */

@Composable
fun RoundedButton(
    modifier: Modifier = Modifier,
    resource: Int,
    contentDescription: String = EMPTY_STRING,
    backGroundColor: Color = Color.Transparent,
    onClick: () -> Unit = { },
) {
    Timber.i("RoundedButton/${contentDescription} Composable")
    Button(
        modifier = modifier.background(color = backGroundColor),
        shape = RoundedCornerShape(50),
        onClick = onClick,
    ) {
        Icon(
            painter = painterResource(id = resource),
            contentDescription = contentDescription,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRoundedButton() {
    RoundedButton(resource = R.drawable.ic_play_transparent_24, contentDescription = "Play")
}