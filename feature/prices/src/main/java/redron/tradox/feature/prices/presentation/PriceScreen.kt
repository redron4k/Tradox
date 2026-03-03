package redron.tradox.feature.prices.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import redron.tradox.feature.prices.mvi.PriceIntent
import redron.tradox.feature.prices.mvi.PriceViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import redron.tradox.core.common.R
import redron.tradox.core.ui.components.TradoxLoadingIndicator
import redron.tradox.core.ui.theme.TradoxTheme
import redron.tradox.feature.prices.presentation.components.PriceItemCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriceScreen(
    viewModel: PriceViewModel,
    onInstrumentClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val state by viewModel.state.collectAsState()
    val lifecycleOwner = LocalLifecycleOwner.current

    val instruments = listOf("AAPL.US", "MSFT.US", "TSLA.US")

    LaunchedEffect(lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.process(PriceIntent.InitPrices(instruments))
            viewModel.process(PriceIntent.Load(instruments))
        }
    }

    DisposableEffect(lifecycleOwner) {
        onDispose { viewModel.process(PriceIntent.StopObserving) }
    }

    Scaffold(
        containerColor = TradoxTheme.colors.background,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.instruments_title),
                        style = TradoxTheme.typography.title2
                    )
                }
            )
        }
    ) { padding ->

        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp)
        ) {

            when {
                state.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        TradoxLoadingIndicator()
                    }
                }

                state.error != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = state.error!!,
                            color = TradoxTheme.colors.error
                        )
                    }
                }

                else -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(vertical = 16.dp)
                    ) {
                        items(state.prices) { quote ->
                            PriceItemCard(
                                symbol = quote.symbol,
                                price = quote.value,
                                source = quote.source,
                                lastUpdate = quote.updateTime,
                                onClick = { onInstrumentClick(quote.symbol) }
                            )
                        }
                    }
                }
            }
        }
    }
}
