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
    primary = Primary1,
    primaryVariant = Primary2,
    primarySoft = Primary4,

    background = Neutral6,
    surface = Neutral6,

    textPrimary = Neutral1,
    textSecondary = Neutral2,
    textDisabled = Neutral4,

    success = SuccessMint,
    error = ErrorRed,
    info = InfoBlue,
    warning = WarningAmber,
    accent = AccentOrange
)

internal val DarkColors = TradoxColors(
    primary = Primary3,
    primaryVariant = Primary2,
    primarySoft = Primary1,

    background = Neutral1,
    surface = Neutral1,

    textPrimary = Neutral6,
    textSecondary = Neutral4,
    textDisabled = Neutral3,

    success = SuccessMint,
    error = ErrorRed,
    info = InfoBlue,
    warning = WarningAmber,
    accent = AccentOrange
)
