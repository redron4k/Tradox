package redron.tradox.domain.repository

import kotlinx.coroutines.flow.StateFlow

interface IPinnedRepository {
    fun toggle(symbol: String)

    fun getPinned(): StateFlow<Set<String>>
}
