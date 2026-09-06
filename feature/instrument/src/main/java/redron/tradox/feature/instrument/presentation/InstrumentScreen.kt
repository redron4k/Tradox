package redron.tradox.feature.instrument.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import redron.tradox.feature.instrument.mvi.InstrumentIntent
import redron.tradox.feature.instrument.mvi.InstrumentViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import redron.tradox.core.common.R
import redron.tradox.core.ui.components.icon.IconBack
import redron.tradox.core.ui.components.TradoxLoadingIndicator
import redron.tradox.core.ui.components.TradoxSegmentedButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InstrumentScreen(
    code: String,
    viewModel: InstrumentViewModel,
    onBackClick: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    var isProMode by remember { mutableStateOf(true) }

    LaunchedEffect(code) {
        viewModel.process(
            InstrumentIntent.Load(code)
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = code) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        IconBack()
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            when {
                state.isLoading -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        TradoxLoadingIndicator()
                    }
                }

                state.error != null -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(text = state.error ?: stringResource(R.string.error_loading))
                    }
                }

                state.details != null -> {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        TradoxSegmentedButton(
                            firstOption = "Lite",
                            secondOption = "Pro",
                            isSecondSelected = isProMode,
                            onOptionSelected = { isProMode = it },
                            modifier = Modifier.padding(horizontal = 16.dp),
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        InstrumentContent(
                            state = state,
                            isProMode = isProMode,
                            modifier = Modifier.weight(1f),
                        )
                    }
                }
            }
        }
    }
}
