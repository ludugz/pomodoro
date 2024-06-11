package ludugz.pomodoro.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import ludugz.pomodoro.R
import ludugz.pomodoro.ui.helpers.Constants.OKAY
import ludugz.pomodoro.ui.theme.RockTypography


/**
 * Created by Tan N. Truong, on 26 December, 2023
 * Email: ludugz@gmail.com
 */

@Composable
fun CheeringDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    val cheeringWords = LocalContext.current.resources.getStringArray(R.array.cheering_words_array)
    val randomIndex = (cheeringWords.indices).random()
    val randomCheeringWord = cheeringWords[randomIndex]

    Dialog(onDismissRequest = { onDismissRequest() }) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Card(
            modifier = Modifier
                .wrapContentSize(),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier.wrapContentSize(),
            ) {
                Text(
                    text = randomCheeringWord,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally),
                    style = RockTypography.bodyLarge,
                    textAlign = TextAlign.Center,
                )
                TextButton(
                    modifier = Modifier
                        .padding(top = 64.dp)
                        .align(Alignment.End),
                    onClick = { onConfirmation() },
                ) {
                    Text(
                        text = OKAY,
                        style = RockTypography.labelSmall,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CheeringDialogPreview() {
    CheeringDialog(
        onDismissRequest = { },
        onConfirmation = { },
    )
}

