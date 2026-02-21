package redon.tradox.core.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import redon.tradox.core.ui.theme.TradoxTheme
import redon.tradox.core.ui.theme.Transparent

@Composable
fun TradoxLoadingIndicator(
    modifier: Modifier = Modifier,
    size: Dp = 40.dp,
    strokeWidth: Dp = 4.dp,
    color: Color = TradoxTheme.colors.primary,
    trackColor: Color = Transparent,
) {
    CircularProgressIndicator(
        modifier = modifier.size(size),
        strokeWidth = strokeWidth,
        color = color,
        trackColor = trackColor,
    )
}
