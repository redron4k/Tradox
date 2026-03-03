package redron.tradox.feature.prices.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.internal.Provider
import redron.tradox.feature.prices.mvi.PriceViewModel
import javax.inject.Inject

class PriceViewModelFactory @Inject constructor(
    private val viewModel: Provider<PriceViewModel>
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModel.get() as T
    }
}
