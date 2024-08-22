package ludugz.pomodoro.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Created by Tan N. Truong, on 22 August, 2024
 * Email: ludugz@gmail.com
 */

@Composable
fun RoundedIcon(imageVector: ImageVector) {
    Card(
        onClick = {},
        shape = RoundedCornerShape(4.dp)
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = "Check Icon"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRoundedIcon() {
    RoundedIcon(imageVector = Icons.Default.Check)
}