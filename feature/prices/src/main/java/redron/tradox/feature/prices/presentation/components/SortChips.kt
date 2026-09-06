package redron.tradox.feature.prices.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import redron.tradox.core.common.R
import redron.tradox.feature.prices.model.SortType

@Composable
fun SortChips(
    selected: SortType,
    onSelected: (SortType) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SortChip(
            textId = R.string.sort_price_up,
            selected = selected == SortType.PriceUp,
            onClick = { onSelected(SortType.PriceUp) }
        )

        SortChip(
            textId = R.string.sort_price_down,
            selected = selected == SortType.PriceDown,
            onClick = { onSelected(SortType.PriceDown) }
        )

        SortChip(
            textId = R.string.sort_name,
            selected = selected == SortType.Name,
            onClick = { onSelected(SortType.Name) }
        )

        SortChip(
            textId = R.string.sort_country,
            selected = selected == SortType.Country,
            onClick = { onSelected(SortType.Country) }
        )
    }
}

@Composable
private fun SortChip(
    @StringRes textId: Int,
    selected: Boolean,
    onClick: () -> Unit
) {
    FilterChip(
        selected = selected,
        onClick = onClick,
        label = {
            Text(
                text = stringResource(textId),
            )
        }
    )
}
