package ru.dw.starvars.utils

import ru.dw.starvars.data.room.PeoplesEntity
import ru.dw.starvars.domain.model.PeoplesItemView
import ru.dw.starvars.domain.model.ResultsItem

const val BASE_URL = "https://swapi.dev/api/"
const val START_PEOPLES_LIST_URL = "https://swapi.dev/api/people/"
const val CONSTANT_ATTRIBUTE_FILMS = "films"
const val CONSTANT_ATTRIBUTE_VEHICLES = "vehicles"
const val CONSTANT_ATTRIBUTE_STARSHIPS = "starships"
const val CONSTANT_ATTRIBUTE_SPECIES = "species"

fun mapperPeoplesListPojoToPeoplesItemView(
    resultsItemPojo: ResultsItem
): PeoplesItemView {
    resultsItemPojo.also { pojo ->
        return PeoplesItemView(
            pojo.films,
            pojo.homeWorld,
            pojo.gender,
            pojo.skinColor,
            pojo.edited,
            pojo.created,
            pojo.mass,
            pojo.vehicles,
            pojo.url,
            pojo.hairColor,
            pojo.birthYear,
            pojo.eyeColor,
            pojo.species,
            pojo.starships,
            pojo.name,
            pojo.height
        )
    }
}

fun mapperItemViewToPeoplesEntity(peoplesItemView: PeoplesItemView): PeoplesEntity {
    return PeoplesEntity(
        id = 0,
        homeWorld = peoplesItemView.homeWorld,
        gender = peoplesItemView.gender,
        skinColor = peoplesItemView.skinColor,
        edited = peoplesItemView.edited,
        created = peoplesItemView.created,
        mass = peoplesItemView.mass,
        url = peoplesItemView.url,
        hairColor = peoplesItemView.hairColor,
        eyeColor = peoplesItemView.eyeColor,
        name = peoplesItemView.name,
        height = peoplesItemView.height
    )

}