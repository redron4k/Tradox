package redron.tradox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import redron.tradox.core.ui.theme.TradoxTheme
import redron.tradox.feature.prices.presentation.PriceScreen

class MainActivity : ComponentActivity() {
    private val pricesComponent by lazy {
        (application as TradoxApp)
            .appComponent
            .pricesComponent()
            .create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val viewModel = pricesComponent.viewModel

        setContent {
            TradoxTheme {
                Scaffold { innerPadding ->
                    PriceScreen(
                        viewModel = viewModel,
                        modifier = Modifier
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}
