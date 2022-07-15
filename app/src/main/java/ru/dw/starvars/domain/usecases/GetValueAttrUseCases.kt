package ru.dw.starvars.domain.usecases

import ru.dw.starvars.data.database.model.ValueAttrEntity
import ru.dw.starvars.domain.repository.RepositoryDetails

class GetValueAttrUseCases(
    private val repositoryDetails: RepositoryDetails
) {
    operator fun invoke(url: String): ValueAttrEntity {
        return repositoryDetails.getValuePlanet(url)
    }
}