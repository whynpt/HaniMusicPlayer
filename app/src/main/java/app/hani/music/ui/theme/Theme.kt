package app.hani.music.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val WarmLightColors: ColorScheme = lightColorScheme(
    primary = Color(0xFFB43F3F),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFDAD7),
    onPrimaryContainer = Color(0xFF410006),
    secondary = Color(0xFF6F5B55),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFADDD4),
    onSecondaryContainer = Color(0xFF281713),
    background = Color(0xFFFFFBF8),
    onBackground = Color(0xFF211A18),
    surface = Color(0xFFFFFBF8),
    onSurface = Color(0xFF211A18),
    surfaceVariant = Color(0xFFF4DDD6),
    onSurfaceVariant = Color(0xFF52433F)
)

private val WarmDarkColors: ColorScheme = darkColorScheme(
    primary = Color(0xFFFFB3AD),
    onPrimary = Color(0xFF69000F),
    primaryContainer = Color(0xFF92272A),
    onPrimaryContainer = Color(0xFFFFDAD7),
    secondary = Color(0xFFDDC2BA),
    onSecondary = Color(0xFF3F2B26),
    secondaryContainer = Color(0xFF57413C),
    onSecondaryContainer = Color(0xFFFADDD4),
    background = Color(0xFF181211),
    onBackground = Color(0xFFF0DEDA),
    surface = Color(0xFF181211),
    onSurface = Color(0xFFF0DEDA),
    surfaceVariant = Color(0xFF52433F),
    onSurfaceVariant = Color(0xFFD7C2BC)
)

@Composable
fun LocalMusicTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) WarmDarkColors else WarmLightColors,
        content = content
    )
}
