package ludugz.pomodoro.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ludugz.pomodoro.ui.helpers.Constants.EMPTY_STRING
import timber.log.Timber

/**
 * Created by Tan N. Truong, on 22 August, 2024
 * Email: ludugz@gmail.com
 */

@Composable
fun RoundedIcon(
    imageVector: ImageVector,
    sizeInDP: Dp = 36.dp,
    contentDescription: String = EMPTY_STRING,
    onClick: () -> Unit,
) {
    Timber.d("RoundedIcon/${contentDescription} Composable")
    Card(
        modifier = Modifier.wrapContentSize(),
        onClick = onClick,
        shape = RoundedCornerShape(4.dp)
    ) {
        Icon(
            modifier = Modifier.size(size = sizeInDP),
            imageVector = imageVector,
            contentDescription = contentDescription
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRoundedIcon() {
    RoundedIcon(
        imageVector = Icons.Default.Check,
        onClick = {}
    )
}