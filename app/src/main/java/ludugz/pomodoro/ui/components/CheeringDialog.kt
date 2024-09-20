package ludugz.pomodoro.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ludugz.pomodoro.domain.model.Quote
import ludugz.pomodoro.ui.helpers.Constants.OKAY
import ludugz.pomodoro.ui.helpers.readJsonFromAssets
import ludugz.pomodoro.ui.theme.DarkCyan
import ludugz.pomodoro.ui.theme.IbmPlexSansTypography
import ludugz.pomodoro.ui.theme.MonospaceTypography
import timber.log.Timber

/**
 * Created by Tan N. Truong, on 26 December, 2023
 * Email: ludugz@gmail.com
 */
@Composable
fun CheeringDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    Timber.i("CheeringDialog Composable")
    val jsonContent: String = readJsonFromAssets(LocalContext.current, "quotes.json")
    val gson = Gson()
    val listType = object : TypeToken<List<Quote>>() {}.type
    val quotes: List<Quote> = gson.fromJson(jsonContent, listType)
    val randomIndex = (quotes.indices).random()
    val randomCheeringWord = quotes[randomIndex]
    var parentHeight by remember { mutableIntStateOf(0) }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .aspectRatio(2f)
                .onGloballyPositioned { coordinates ->
                    parentHeight = coordinates.size.height
                },
            shape = RoundedCornerShape(32.dp),
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .wrapContentHeight()
                        .padding(vertical = 8.dp)
                        .background(color = Color.Transparent)
                        .align(Alignment.Center),
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = randomCheeringWord.description,
                        style = MonospaceTypography.bodyMedium,
                        fontWeight = FontWeight.Thin,
                        fontSize = 12.sp,
                        color = DarkCyan,
                        textAlign = TextAlign.Start,
                    )

                }
                TextButton(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    onClick = { onConfirmation() },
                ) {
                    Text(
                        text = OKAY,
                        style = IbmPlexSansTypography.bodyMedium,
                        color = Color.Black,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CheeringDialogPreview() {
    CheeringDialog(
        onDismissRequest = { },
        onConfirmation = { },
    )
}
