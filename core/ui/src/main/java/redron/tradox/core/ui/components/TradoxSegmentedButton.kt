package redron.tradox.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import redron.tradox.core.ui.theme.TradoxTheme

@Composable
fun TradoxSegmentedButton(
    firstOption: String,
    secondOption: String,
    isSecondSelected: Boolean,
    onOptionSelected: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    cornerRadius: Int = 16
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = TradoxTheme.colors.surface,
                shape = RoundedCornerShape(cornerRadius.dp)
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1f)
                .clickable { onOptionSelected(false) }
                .background(
                    color = if (!isSecondSelected) TradoxTheme.colors.primary else Color.Transparent,
                    shape = RoundedCornerShape(
                        topStart = cornerRadius.dp,
                        bottomStart = cornerRadius.dp
                    )
                )
                .padding(vertical = 10.dp)
        ) {
            Text(
                text = firstOption,
                color = if (!isSecondSelected) TradoxTheme.colors.background else TradoxTheme.colors.textPrimary,
                style = TradoxTheme.typography.body1
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1f)
                .clickable { onOptionSelected(true) }
                .background(
                    color = if (isSecondSelected) TradoxTheme.colors.primary else Color.Transparent,
                    shape = RoundedCornerShape(
                        topEnd = cornerRadius.dp,
                        bottomEnd = cornerRadius.dp
                    )
                )
                .padding(vertical = 10.dp)
        ) {
            Text(
                text = secondOption,
                color = if (isSecondSelected) TradoxTheme.colors.background else TradoxTheme.colors.textPrimary,
                style = TradoxTheme.typography.body1
            )
        }
    }
}
