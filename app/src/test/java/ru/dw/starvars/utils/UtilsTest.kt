package ru.dw.starvars.utils

import org.junit.Assert.*

import org.junit.Test
import ru.dw.starvars.data.network.model.ResultsItem
import ru.dw.starvars.data.database.model.PeopleDbModel
import ru.dw.starvars.domain.model.PeoplesItemView
import ru.dw.starvars.utils.Utils.VIEW_TAPE_CHARACTER


class UtilsTest {
    private val peoplesEntity  = PeopleDbModel(
        0,
        "https://swapi.dev/api/planets/1/",
        "male",
        "blue",
        "2014-12-20T21:17:56.891000Z",
        "2014-12-09T13:50:51.644000Z",
        "77",
        "https://swapi.dev/api/people/1/",
        "blond",
        "19BBY",
        "blue",
        "Luke Skywalker",
        "172",
        VIEW_TAPE_CHARACTER,
        "https://swapi.dev/api/people/?page=2"
    )
    private val peoplesItemView = PeoplesItemView(
        0,
        "https://swapi.dev/api/planets/1/",
        "male",
        "blue",
        "2014-12-20T21:17:56.891000Z",
        "2014-12-09T13:50:51.644000Z",
        "77",
        "https://swapi.dev/api/people/1/",
        "blond",
        "19BBY",
        "blue",
        "Luke Skywalker",
        "172",
        VIEW_TAPE_CHARACTER,
        "https://swapi.dev/api/people/?page=2"
    )
    private val resultsItem = ResultsItem(
        homeWorld = "https://swapi.dev/api/planets/1/",
        gender = "male",
        skinColor = "blue",
        edited = "2014-12-20T21:17:56.891000Z",
        created = "2014-12-09T13:50:51.644000Z",
        mass = "77",
        url = "https://swapi.dev/api/people/1/",
        hairColor = "blond",
        birthYear = "19BBY",
        eyeColor = "blue",
        name = "Luke Skywalker",
        height = "172",
    )

    @Test
    fun `fun mapperPeoplesEntityToPeoplesItemView convert PeoplesEntity return PeoplesItemView`() {
//        val result =  mapperPeoplesEntityToPeoplesItemView(peoplesEntity)
//        assertEquals(peoplesItemView,result)
    }

    @Test
    fun `fun convertPogoToEntity ResultsItem convert  return PeoplesEntity`() {
//        val result = convertPogoToEntity(resultsItem)
//        val itemEntity = peoplesEntity
//         itemEntity.nextPage = null
//        assertEquals(itemEntity,result)
    }
}