package ru.dw.starvars.domain.cases



import ru.dw.starvars.data.repositories.list.RepositoryIpl

class GetListCharacterCase(private val repository: RepositoryIpl) {
    operator fun invoke(url:String, requestError: (String) -> Unit){
        repository.getRequestUrl(url,requestError)
    }
}