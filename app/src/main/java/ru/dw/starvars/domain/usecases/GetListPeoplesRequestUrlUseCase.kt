package ru.dw.starvars.domain.usecases



import ru.dw.starvars.domain.repository.RepositoryList
import ru.dw.starvars.presentation.viewmodel.list.ListPeoplesViewModel

class GetListPeoplesRequestUrlUseCase(private val repository: RepositoryList) {
    operator fun invoke(
        url: String,
        responseCallBackViewModel: ListPeoplesViewModel.ResponseCallBackViewModel
    ) {
        return repository.getRequestUrl(url,responseCallBackViewModel)
    }
}