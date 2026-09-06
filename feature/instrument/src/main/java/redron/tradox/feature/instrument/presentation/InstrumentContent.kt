package redron.tradox.feature.instrument.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import redron.tradox.core.common.R
import redron.tradox.feature.instrument.mvi.InstrumentState
import redron.tradox.feature.instrument.presentation.components.InstrumentChart
import redron.tradox.feature.instrument.presentation.components.InstrumentStaticInfo

@Composable
fun InstrumentContent(
    state: InstrumentState,
    isProMode: Boolean,
    modifier: Modifier = Modifier
) {
    val details = state.details ?: return
    val lastPoint = details.points.lastOrNull()

    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        item() {
            Text(
                text = details.code,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            lastPoint?.let {
                val change = it.closePrice - it.openPrice
                val changePercent =
                    if (it.openPrice != 0.0)
                        (change / it.openPrice) * 100
                    else 0.0

                val changeColor =
                    if (change >= 0)
                        Color(0xFF2E7D32)
                    else
                        Color(0xFFC62828)

                Column {
                    Text(
                        text = stringResource(R.string.price_format, it.closePrice),
                        style = MaterialTheme.typography.headlineLarge
                    )

                    Text(
                        text = stringResource(R.string.change_format, change, changePercent),
                        color = changeColor
                    )
                }
            }
        }

        item() {
            Spacer(modifier = Modifier.height(24.dp))

            InstrumentChart(
                points = details.points,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                isProMode = isProMode
            )
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))

            details.staticDetails?.let {
                InstrumentStaticInfo(
                    staticDetails = it
                )
            }
        }
    }
}
