package redron.tradox.feature.instrument.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.internal.Provider
import redron.tradox.feature.instrument.mvi.InstrumentViewModel
import javax.inject.Inject

class InstrumentViewModelFactory @Inject constructor(
    private val viewModel: Provider<InstrumentViewModel>
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModel.get() as T
    }
}
