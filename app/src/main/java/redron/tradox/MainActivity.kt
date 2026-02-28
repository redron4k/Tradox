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
import kotlin.getValue

class MainActivity : ComponentActivity() {
    private val pricesComponent by lazy {
        (application as TradoxApp)
            .appComponent
            .pricesComponent()
            .create()
    }

    private val instrumentComponent by lazy {
        (application as TradoxApp)
            .appComponent
            .instrumentComponent()
            .create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val pricesViewModel = pricesComponent.viewModel
        val instrumentViewModel = instrumentComponent.viewModel

        setContent {
            TradoxTheme {
                Scaffold { innerPadding ->
                    PriceScreen(
                        viewModel = pricesViewModel,
                        modifier = Modifier
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}
