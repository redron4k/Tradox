package redron.tradox.core.ui.components.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.material.icons.outlined.PushPin
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import redron.tradox.core.ui.theme.TradoxTheme

@Composable
fun IconPin(
    isPinned: Boolean,
) {
    Icon(
        imageVector = if (isPinned) Icons.Default.PushPin else Icons.Outlined.PushPin,
        contentDescription = "Pin",
        tint = TradoxTheme.colors.textSecondary
    )
}
