package ru.dw.starvars.domain.cases


import ru.dw.starvars.data.room.entity.ValueAttrEntity
import ru.dw.starvars.domain.RepositoryDetails

class GetAttrAllCase(private val repository: RepositoryDetails) {

    operator fun invoke(url:String): ValueAttrEntity{
       return repository.getValueAttr(url)
    }
}