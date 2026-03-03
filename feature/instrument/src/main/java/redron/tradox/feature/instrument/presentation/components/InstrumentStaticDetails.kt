package redron.tradox.feature.instrument.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import redron.tradox.core.common.R
import redron.tradox.core.ui.components.icon.IconHintButton
import redron.tradox.domain.model.instrument.InstrumentStaticDetails

@Composable
fun InstrumentStaticInfo(
    staticDetails: InstrumentStaticDetails,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {

        Text(
            text = stringResource(R.string.static_info_title),
            style = MaterialTheme.typography.titleMedium,
        )

        Spacer(modifier = Modifier.height(16.dp))

        InfoRow(
            label = stringResource(R.string.sector),
            value = staticDetails.sector,
        )

        InfoRow(
            label = stringResource(R.string.currency),
            value = staticDetails.currency,
        )

        InfoRow(
            label = stringResource(R.string.dividend),
            value = staticDetails.dividend?.toString(),
            hintId = R.string.hint_dividend,
        )

        InfoRow(
            label = stringResource(R.string.eps),
            value = staticDetails.earningsPerShare?.toString(),
            hintId = R.string.hint_eps,
        )

        InfoRow(
            label = stringResource(R.string.eps_ttm),
            value = staticDetails.earningsPerShareTTM?.toString(),
            hintId = R.string.hint_eps_ttm,
        )

        InfoRow(
            label = stringResource(R.string.total_shares),
            value = staticDetails.totalShares?.toString(),
            hintId = R.string.hint_total_shares,
        )

        InfoRow(
            label = stringResource(R.string.lot_size),
            value = staticDetails.lotSize?.toString(),
            hintId = R.string.hint_lot_size,
        )
    }
}

@Composable
private fun InfoRow(
    label: String,
    value: String?,
    modifier: Modifier = Modifier,
    @StringRes hintId: Int? = null,
) {
    if (value.isNullOrBlank()) return

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            if (hintId != null) {
                Spacer(modifier = Modifier.width(4.dp))
                IconHintButton(hintTextResId = hintId)
            }
        }


        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
