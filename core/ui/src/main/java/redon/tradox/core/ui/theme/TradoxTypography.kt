package redon.tradox.core.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class TradoxTypography(
    val title1: TextStyle,
    val title2: TextStyle,
    val title3: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
    val body3: TextStyle,
    val caption1: TextStyle,
    val caption2: TextStyle,
)

internal val DefaultTypography = TradoxTypography(

    title1 = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold
    ),

    title2 = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold
    ),

    title3 = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold
    ),

    body1 = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium
    ),

    body2 = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    ),

    body3 = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    ),

    caption1 = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold
    ),

    caption2 = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    )
)

val LocalTradoxTypography =
    staticCompositionLocalOf { DefaultTypography }
