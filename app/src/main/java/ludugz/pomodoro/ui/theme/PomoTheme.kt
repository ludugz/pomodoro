package ludugz.pomodoro.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val DarkColorScheme = darkColorScheme(
    primary = MidnightBlue,
    secondary = SkyBlue,
    tertiary = AquaBlue,
    onSurface = SoftWhite,
)

// Light Theme Color Scheme
val LightColorScheme = lightColorScheme(
    primary = Color(0xFF81C784),
    onPrimary = Color(0xFF003300),
    primaryContainer = Color(0xFFA5D6A7),
    onPrimaryContainer = Color(0xFF1B5E20),
    secondary = Color(0xFF66BB6A),
    onSecondary = Color(0xFF1B5E20),
    secondaryContainer = Color(0xFF81C784),
    onSecondaryContainer = Color(0xFF003300),
    background = LightBackground,
    onBackground = Color.Black,
    surface = LightSurface,
    onSurface = Color.Black,
    error = ErrorRed,
    onError = Color.White
)

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */

@Composable
fun PomodoroidTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    return MaterialTheme(
        colorScheme = colorScheme,
        typography = MonospaceTypography,
        content = content
    )
}