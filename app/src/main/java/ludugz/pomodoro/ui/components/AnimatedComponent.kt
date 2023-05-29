package ludugz.pomodoro.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable

/**
 * Created by Tan N. Truong, on 29 May, 2023
 * Email: ludugz@gmail.com
 */
@Composable
fun AnimatedComponent(
    shouldShow: Boolean = false,
    duration: Int = 500,
    content: @Composable () -> Unit,
) {
    val inAnimationSpec: FiniteAnimationSpec<Float> = tween(durationMillis = duration)
    val outAnimationSpec: FiniteAnimationSpec<Float> =
        tween(durationMillis = duration, delayMillis = duration)
    AnimatedVisibility(
        visible = shouldShow,
        enter = fadeIn(animationSpec = if (shouldShow) inAnimationSpec else outAnimationSpec),
        exit = fadeOut(animationSpec = if (shouldShow) inAnimationSpec else outAnimationSpec),
    ) {
        content()
    }
}