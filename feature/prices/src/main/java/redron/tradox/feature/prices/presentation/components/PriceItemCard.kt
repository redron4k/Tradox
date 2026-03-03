package redron.tradox.feature.prices.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.LocalDateTime
import redron.tradox.core.ui.components.TradoxCard
import redron.tradox.core.ui.components.icon.IconPin
import redron.tradox.core.ui.theme.TradoxTheme

@Composable
fun PriceItemCard(
    symbol: String,
    price: Double,
    startPrice: Double,
    source: String,
    lastUpdate: LocalDateTime,
    isPinned: Boolean,
    onClick: () -> Unit,
    onPin: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val change = price - startPrice

    val changeColor = if (change >= 0)
            TradoxTheme.colors.success
        else
            TradoxTheme.colors.error

    TradoxCard(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {

        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = symbol,
                    style = TradoxTheme.typography.title3,
                    color = TradoxTheme.colors.textPrimary
                )

                IconButton(
                    onClick = onPin,
                ) {
                    IconPin(isPinned)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "%.2f".format(price),
                    style = TradoxTheme.typography.title2,
                    color = TradoxTheme.colors.textPrimary
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = "%+.2f".format(change),
                    style = TradoxTheme.typography.body1,
                    color = changeColor
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = source,
                    style = TradoxTheme.typography.caption1,
                    color = TradoxTheme.colors.textSecondary
                )

                Text(
                    text = "%02d:%02d:%02d".format(
                        lastUpdate.hour,
                        lastUpdate.minute,
                        lastUpdate.second
                    ),
                    style = TradoxTheme.typography.caption1,
                    color = TradoxTheme.colors.textSecondary
                )
            }
        }
    }
}
