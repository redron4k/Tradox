package redron.tradox.feature.prices

import dagger.Subcomponent
import redron.tradox.feature.prices.mvi.PriceViewModel

@Subcomponent
interface PricesComponent {

    val viewModel: PriceViewModel

    @Subcomponent.Factory
    interface Factory {
        fun create(): PricesComponent
    }
}
