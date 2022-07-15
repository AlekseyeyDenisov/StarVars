package ru.dw.starvars.data.mapper

import ru.dw.starvars.data.database.model.PeopleDbModel
import ru.dw.starvars.data.network.model.ResultsItem
import ru.dw.starvars.domain.model.PeoplesItemView
import ru.dw.starvars.utils.Utils

class StarWarsMapper {

    fun mapPogoToEntity(resultsItem: ResultsItem): PeopleDbModel {
        return PeopleDbModel(
            0,
            resultsItem.homeWorld,
            resultsItem.gender,
            resultsItem.skinColor,
            resultsItem.edited,
            resultsItem.created,
            resultsItem.mass,
            resultsItem.url,
            resultsItem.hairColor,
            resultsItem.birthYear,
            resultsItem.eyeColor,
            resultsItem.name,
            resultsItem.height,
            Utils.VIEW_TAPE_CHARACTER
        )

    }

    fun mapDbModelToEntity(peopleDbModel: PeopleDbModel) = PeoplesItemView(
        peopleDbModel.id,
        peopleDbModel.homeWorld,
        peopleDbModel.gender,
        peopleDbModel.skinColor,
        peopleDbModel.edited,
        peopleDbModel.created,
        peopleDbModel.mass,
        peopleDbModel.url,
        peopleDbModel.hairColor,
        peopleDbModel.birthYear,
        peopleDbModel.eyeColor,
        peopleDbModel.name,
        peopleDbModel.height,
        peopleDbModel.viewTape,
        peopleDbModel.nextPage
    )


    fun mapListDbModelToListEntity(list: List<PeopleDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}