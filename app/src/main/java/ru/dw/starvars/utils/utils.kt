package ru.dw.starvars.utils



import ru.dw.starvars.data.room.entity.PeoplesEntity
import ru.dw.starvars.domain.model.PeoplesItemView
import ru.dw.starvars.domain.model.ResultsItem


const val BASE_URL = "https://swapi.dev/api/"
const val START_PEOPLES_LIST_URL = "https://swapi.dev/api/people/"
const val CONSTANT_ATTRIBUTE_FILMS = "films"
const val CONSTANT_ATTRIBUTE_VEHICLES = "vehicles"
const val CONSTANT_ATTRIBUTE_STARSHIPS = "starships"
const val CONSTANT_ATTRIBUTE_SPECIES = "species"
const val CONSTANT_TABLE_PEOPLES = "peoples"
const val VIEW_TAPE_CHARACTER = 1
const val VIEW_TAPE_LOAD_MORE = 0


fun mapperPeoplesEntityToPeoplesItemView(peoplesEntity: PeoplesEntity): PeoplesItemView {
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

fun convertPogoToEntity(resultsItem: ResultsItem): PeoplesEntity {
    return PeoplesEntity(
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


