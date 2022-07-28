package ru.dw.starvars.domain.cases


import ru.dw.starvars.data.repositories.list.RepositoryIpl

class RefreshListCharacterCase(private val repository: RepositoryIpl) {

    operator fun invoke() {
        repository.refresh()
    }
}