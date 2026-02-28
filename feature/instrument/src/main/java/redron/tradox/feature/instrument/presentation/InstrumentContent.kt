package redron.tradox.feature.instrument.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import redron.tradox.feature.instrument.mvi.InstrumentState
import redron.tradox.feature.instrument.presentation.components.DividendItem

@Composable
fun InstrumentContent(
    state: InstrumentState,
    modifier: Modifier = Modifier,
) {

    val instrument = state.details!!

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .fillMaxSize(),
    ) {
        item {
            Text(
                text = instrument.code,
                style = MaterialTheme.typography.headlineMedium
            )
        }

        item {

            Text(
                text = instrument.name,
                style = MaterialTheme.typography.titleLarge
            )
        }

        instrument.description?.let {

            item {

                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        instrument.currency?.let {

            item {

                Text(
                    text = "Currency: $it"
                )
            }
        }

        if (instrument.dividends.isNotEmpty()) {

            item {

                Text(
                    text = "Dividends",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            items(instrument.dividends) {
                DividendItem(it)
            }
        }
    }
}