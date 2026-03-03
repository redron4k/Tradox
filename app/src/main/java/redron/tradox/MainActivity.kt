package redron.tradox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import redron.tradox.core.ui.theme.TradoxTheme
import redron.tradox.presentation.MainScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TradoxTheme {
                MainScreen()
            }
        }
    }
}
