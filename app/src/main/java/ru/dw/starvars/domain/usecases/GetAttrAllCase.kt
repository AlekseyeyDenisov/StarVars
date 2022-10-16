package ru.dw.starvars.domain.usecases


import ru.dw.starvars.data.database.entity.ValueAttrEntity
import ru.dw.starvars.domain.RepositoryDetails
import javax.inject.Inject

class GetAttrAllCase @Inject constructor(
    private val repository: RepositoryDetails
    ) {

    operator fun invoke(url:String): ValueAttrEntity{
       return repository.getValueAttr(url)
    }
}