package ru.dw.starvars.domain.usecases


import ru.dw.starvars.data.repositories.list.RepositoryListIpl
import javax.inject.Inject

class RefreshListCharacterCase @Inject constructor(private val repository: RepositoryListIpl) {

    operator fun invoke() {
        repository.refresh()
    }
}