package ru.dw.starvars.domain.cases


import ru.dw.starvars.domain.RepositoryDetails

class GetRequestCase(private val repository: RepositoryDetails) {

    operator fun invoke(url: String,attr: String, name:(String)->Unit){
       return repository.getRequestUrl(url,attr){
           name(it)
       }
    }
}