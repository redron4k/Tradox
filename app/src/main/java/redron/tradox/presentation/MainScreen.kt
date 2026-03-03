package redron.tradox.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import redron.tradox.navigation.AppNavHost

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    AppNavHost(
        navController = navController,
        modifier = Modifier
            .padding(horizontal = 8.dp)
    )
}
