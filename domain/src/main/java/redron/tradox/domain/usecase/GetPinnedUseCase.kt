package redron.tradox.domain.usecase

import kotlinx.coroutines.flow.StateFlow
import redron.tradox.domain.repository.IPinnedRepository

class GetPinnedUseCase(
    private val repository: IPinnedRepository,
) {
    operator fun invoke(): StateFlow<Set<String>> {
        return repository.getPinned()
    }
}
