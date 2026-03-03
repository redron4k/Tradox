package redron.tradox.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import redron.tradox.navigation.AppNavHost

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    AppNavHost(
        navController = navController,
    )
}
