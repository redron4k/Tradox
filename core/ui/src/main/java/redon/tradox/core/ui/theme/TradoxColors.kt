package redon.tradox.core.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class TradoxColors(

    // Primary
    val primary: Color,
    val primaryVariant: Color,
    val primarySoft: Color,

    // Background
    val background: Color,
    val surface: Color,

    // Text
    val textPrimary: Color,
    val textSecondary: Color,
    val textDisabled: Color,

    // Semantic
    val success: Color,
    val error: Color,
    val info: Color,
    val warning: Color,
    val accent: Color
)

val LocalTradoxColors = staticCompositionLocalOf<TradoxColors> {
    error("TradoxColors not provided")
}

internal val LightColors = TradoxColors(
    primary = Primary900,
    primaryVariant = Primary700,
    primarySoft = Primary100,

    background = Neutral0,
    surface = Neutral0,

    textPrimary = Neutral900,
    textSecondary = Neutral700,
    textDisabled = Neutral300,

    success = SuccessMint,
    error = ErrorRed,
    info = InfoBlue,
    warning = WarningAmber,
    accent = AccentOrange
)

internal val DarkColors = TradoxColors(
    primary = Primary500,
    primaryVariant = Primary700,
    primarySoft = Primary900,

    background = Neutral900,
    surface = Neutral900,

    textPrimary = Neutral0,
    textSecondary = Neutral300,
    textDisabled = Neutral600,

    success = SuccessMint,
    error = ErrorRed,
    info = InfoBlue,
    warning = WarningAmber,
    accent = AccentOrange
)
