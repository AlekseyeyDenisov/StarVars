package ru.dw.starvars.utils


import ru.dw.starvars.domain.model.PeoplesItemView
import ru.dw.starvars.data.retrofit.model.ResultsItem
import ru.dw.starvars.data.room.PeopleDbModel

object Utils {
    const val BASE_URL = "https://swapi.dev/api/"
    const val START_PEOPLES_LIST_URL = "https://swapi.dev/api/people/"

    const val CONSTANT_TABLE_PEOPLES = "peoples"
    const val VIEW_TAPE_CHARACTER = 1
    const val VIEW_TAPE_LOAD_MORE = 0

    fun mapperPeoplesEntityToPeoplesItemView(peoplesEntity: PeopleDbModel): PeoplesItemView {
        return PeoplesItemView(
            peoplesEntity.id,
            peoplesEntity.homeWorld,
            peoplesEntity.gender,
            peoplesEntity.skinColor,
            peoplesEntity.edited,
            peoplesEntity.created,
            peoplesEntity.mass,
            peoplesEntity.url,
            peoplesEntity.hairColor,
            peoplesEntity.birthYear,
            peoplesEntity.eyeColor,
            peoplesEntity.name,
            peoplesEntity.height,
            peoplesEntity.viewTape,
            peoplesEntity.nextPage
        )
    }

    fun convertPogoToEntity(resultsItem: ResultsItem): PeopleDbModel {
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
            VIEW_TAPE_CHARACTER
        )

    }
}