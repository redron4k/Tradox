package redron.tradox.core.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

object TradoxTheme {

    val colors: TradoxColors
        @Composable
        @ReadOnlyComposable
        get() = LocalTradoxColors.current

    val typography: TradoxTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTradoxTypography.current

    val shapes: TradoxShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalTradoxShapes.current
}

@Composable
fun TradoxTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current

    val materialColorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }
        else -> {
            if (darkTheme) darkColorScheme()
            else lightColorScheme()
        }
    }

    val tokenColors = remember(darkTheme) {
        if (darkTheme) DarkColors else LightColors
    }

    CompositionLocalProvider(
        LocalTradoxColors provides tokenColors,
        LocalTradoxTypography provides DefaultTypography,
        LocalTradoxShapes provides DefaultShapes
    ) {
        MaterialTheme(
            colorScheme = materialColorScheme,
            content = content
        )
    }
}
