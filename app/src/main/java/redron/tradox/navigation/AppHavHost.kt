package redron.tradox.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import redron.tradox.TradoxApp
import redron.tradox.feature.instrument.mvi.InstrumentViewModel
import redron.tradox.feature.instrument.presentation.InstrumentScreen
import redron.tradox.feature.prices.mvi.PriceViewModel
import redron.tradox.feature.prices.presentation.PriceScreen


@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val appComponent =
        (context.applicationContext as TradoxApp)
            .appComponent

    NavHost(
        navController = navController,
        startDestination = NavRoutes.PRICES,
        modifier = modifier,
    ) {
        composable(
            route = NavRoutes.PRICES
        ) {

            val component = remember {
                appComponent.pricesComponent().create()
            }

            val viewModel = viewModel<PriceViewModel>(
                viewModelStoreOwner = it,
                factory = component.viewModelFactory,
            )

            PriceScreen(
                viewModel = viewModel,
                onInstrumentClick = { code ->
                    navController.navigate(
                        NavRoutes.instrument(code)
                    )
                }
            )
        }

        composable(
            route = "${NavRoutes.INSTRUMENT}/{${NavArguments.CODE}}"
        ) { backStackEntry ->
            val code = backStackEntry.arguments
                ?.getString(NavArguments.CODE)
                ?: return@composable

            val component = remember {
                appComponent.instrumentComponent().create()
            }

            val viewModel = viewModel<InstrumentViewModel>(
                viewModelStoreOwner = backStackEntry,
                factory = component.viewModelFactory,
            )

            InstrumentScreen(
                code = code,
                viewModel = viewModel,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
