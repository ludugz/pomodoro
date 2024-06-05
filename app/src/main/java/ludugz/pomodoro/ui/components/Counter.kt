package ludugz.pomodoro.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


/**
 * Created by Tan N. Truong, on 03 June, 2024
 * Email: ludugz@gmail.com
 */
@Composable
fun Counter(
    modifier: Modifier = Modifier,
    counter: Int = 0,
) {
    Text(
        modifier = modifier,
        text = counter.toString()
    )
}

@Preview
@Composable
fun PreviewCounter() {
    Counter(counter = 99)
}