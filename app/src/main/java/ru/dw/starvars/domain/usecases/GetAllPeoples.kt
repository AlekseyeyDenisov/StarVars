package ru.dw.starvars.domain.usecases

import androidx.lifecycle.LiveData
import ru.dw.starvars.domain.model.PeoplesItemView
import ru.dw.starvars.domain.repository.RepositoryList

class GetAllPeoples(private val repository: RepositoryList)
{
    operator fun invoke(): LiveData<List<PeoplesItemView>> {
        return repository.getAllPeoples()
    }
}