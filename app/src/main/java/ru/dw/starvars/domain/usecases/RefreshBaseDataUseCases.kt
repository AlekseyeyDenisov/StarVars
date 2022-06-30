package ru.dw.starvars.domain.usecases

import ru.dw.starvars.domain.repository.RepositoryList

class RefreshBaseDataUseCases(
    private val repository: RepositoryList
) {
    operator fun invoke(){
        repository.refresh()
    }
}