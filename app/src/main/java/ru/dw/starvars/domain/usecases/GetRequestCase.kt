package ru.dw.starvars.domain.usecases


import ru.dw.starvars.domain.RepositoryDetails
import javax.inject.Inject

class GetRequestCase @Inject constructor(
    private val repository: RepositoryDetails
    ) {

    operator fun invoke(url: String,attr: String, name:(String)->Unit){
       return repository.getRequestUrl(url,attr){
           name(it)
       }
    }
}