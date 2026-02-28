package redron.tradox.feature.instrument.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import redron.tradox.domain.model.instrument.Dividend

@Composable
fun DividendItem(
    dividend: Dividend,
    modifier: Modifier = Modifier,
) {

    Card(
        modifier = modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(12.dp)
        ) {

            Text("Date: ${dividend.date}")

            Text("Amount: ${dividend.amount}")
        }
    }
}