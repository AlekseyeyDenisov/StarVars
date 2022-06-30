package ru.dw.starvars.domain.usecases

import ru.dw.starvars.data.room.entity.ValueAttrEntity
import ru.dw.starvars.domain.repository.RepositoryDetails

class GetValueAttrUseCases(
    private val repositoryDetails: RepositoryDetails
) {
    operator fun invoke(url: String): ValueAttrEntity {
        return repositoryDetails.getValueAttr(url)
    }
}