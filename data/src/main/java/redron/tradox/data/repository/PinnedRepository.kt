package redron.tradox.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import redron.tradox.domain.repository.IPinnedRepository

class PinnedRepository : IPinnedRepository {

    private val _pinned = MutableStateFlow<Set<String>>(emptySet())

    override fun toggle(symbol: String) {
        _pinned.update { current ->
            if (symbol in current) current - symbol
            else current + symbol
        }
    }

    override fun getPinned(): StateFlow<Set<String>> {
        return _pinned.asStateFlow()
    }
}
