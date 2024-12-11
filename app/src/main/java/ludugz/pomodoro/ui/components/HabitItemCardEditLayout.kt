package ludugz.pomodoro.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ludugz.pomodoro.ui.helpers.Constants.OKAY
import ludugz.pomodoro.ui.theme.MonospaceTypography
import timber.log.Timber

/**
 * Created by Tan N. Truong, on 26 December, 2023
 * Email: ludugz@gmail.com
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitItemCardEditLayout(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    Timber.d("HabitItemCardEditLayout Composable")
    var sessionName by remember { mutableStateOf(name) }
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .pointerInput(Unit) {
                detectTapGestures {
                    focusManager.clearFocus()
                }
            }
            .verticalScroll(state = scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier
                .align(alignment = Alignment.End)
                .padding(16.dp)
                .clickable { onDismissRequest() },
            imageVector = Icons.Default.Close,
            contentDescription = "Close Icon"
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = "Session Name",
            style = MonospaceTypography.labelLarge,
            textAlign = TextAlign.Center,
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    vertical = 8.dp,
                    horizontal = 24.dp
                )
                .background(Color.Transparent),
            value = sessionName,
            onValueChange = {
                sessionName = it
            }
        )

        Text(
            modifier = Modifier.padding(8.dp),
            text = "Background Color",
            style = MonospaceTypography.labelLarge,
            textAlign = TextAlign.Center,
        )

        ColorsGrid(
            itemPadding = 2.dp,
            itemSize = 32.dp,
            chunkedColumn = 9
        )

        TextButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(size = 16.dp))
                .background(MaterialTheme.colorScheme.primary),
            onClick = { onConfirmation() },
            interactionSource = remember { MutableInteractionSource() },
            colors = ButtonDefaults.textButtonColors(
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(
                text = OKAY,
                style = MonospaceTypography.titleSmall,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardItemEditDialogPreview() {
    HabitItemCardEditLayout(
        onDismissRequest = { },
        onConfirmation = { },
    )
}
