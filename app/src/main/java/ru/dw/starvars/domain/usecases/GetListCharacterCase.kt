package ru.dw.starvars.domain.usecases


import ru.dw.starvars.data.repositories.list.RepositoryListIpl
import javax.inject.Inject

class GetListCharacterCase @Inject constructor(private val repository: RepositoryListIpl) {
    operator fun invoke(url: String, requestError: (String) -> Unit) {
        repository.getRequestUrl(url, requestError)
    }
}