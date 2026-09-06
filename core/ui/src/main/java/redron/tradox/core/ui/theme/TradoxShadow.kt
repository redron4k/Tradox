package redron.tradox.core.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object TradoxShadow {

    val Card = ShadowSpec(
        blur = 30.dp,
        offsetY = 4.dp,
        color = Color(0x123629B7)
    )

    val CardSmall = ShadowSpec(
        blur = 30.dp,
        offsetY = (-5).dp,
        color = Color(0x123629B7)
    )
}

data class ShadowSpec(
    val blur: androidx.compose.ui.unit.Dp,
    val offsetY: androidx.compose.ui.unit.Dp,
    val color: Color
)
