package redron.tradox.feature.instrument.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import redron.tradox.feature.instrument.mvi.InstrumentIntent
import redron.tradox.feature.instrument.mvi.InstrumentViewModel
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import redron.tradox.core.ui.components.TradoxLoadingIndicator

@Composable
fun InstrumentScreen(
    code: String,
    viewModel: InstrumentViewModel,
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(code) {
        viewModel.process(
            InstrumentIntent.Load(code)
        )
    }

    when {
        state.isLoading -> {
            Box(
                contentAlignment = androidx.compose.ui.Alignment.Center,
                modifier = Modifier.fillMaxSize(),
            ) {
                TradoxLoadingIndicator()
            }
        }

        state.error != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Text(state.error!!)
            }
        }

        state.details != null -> {
            InstrumentContent(
                state = state,
            )
        }
    }
}
