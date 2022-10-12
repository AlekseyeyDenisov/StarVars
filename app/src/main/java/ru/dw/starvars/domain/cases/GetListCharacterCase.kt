package ru.dw.starvars.domain.cases



import ru.dw.starvars.data.repositories.list.RepositoryIpl
import ru.dw.starvars.pressentation.view.list.list.ListCharactersViewModel

class GetListCharacterCase(private val repository: RepositoryIpl) {
    operator fun invoke(url:String, responseCallBackViewModel: ListCharactersViewModel.ResponseCallBackViewModel){
        repository.getRequestUrl(url,responseCallBackViewModel)
    }
}