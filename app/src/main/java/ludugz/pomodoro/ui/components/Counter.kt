package ludugz.pomodoro.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


/**
 * Created by Tan N. Truong, on 03 June, 2024
 * Email: ludugz@gmail.com
 */
@Composable
fun Counter(
    modifier: Modifier = Modifier,
    counter: Int = 0,
) {
    var scale by remember { mutableFloatStateOf(1f) }
    LaunchedEffect(key1 = counter) {
        scale = 2f
        delay(300)
        scale = 1f
    }
    var animatedScale = animateFloatAsState(
        targetValue = scale,
        label = "animated scale"
    )
    Text(
        modifier = modifier.scale(scale = animatedScale.value),
        text = counter.toString(),
        fontSize = 24.sp,
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCounter() {
    Counter(counter = 99)
}