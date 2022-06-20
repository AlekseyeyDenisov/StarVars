package ru.dw.utils

import ru.dw.starvars.domain.model.PeoplesItemView
import ru.dw.starvars.domain.model.ResultsItem

const val BASE_URL = "https://swapi.dev/api/"
const val START_PEOPLES_LIST_URL = "https://swapi.dev/api/people/"

fun mapperPeoplesListPojoToPeoplesItemView(
    resultsItemPojo: ResultsItem): PeoplesItemView {
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