package ru.dw.starvars.utils



import ru.dw.starvars.data.room.entity.CharactersEntity
import ru.dw.starvars.domain.model.CharacterItemView
import ru.dw.starvars.domain.model.ResultsItem


const val BASE_URL = "https://swapi.dev/api/"
const val START_CHAPTERS_LIST_URL = "https://swapi.dev/api/people/"
const val CONSTANT_ATTRIBUTE_HOME_WORLD = "homeworld"

const val CONSTANT_TABLE_CHAPTERS = "chapters"
const val VIEW_TAPE_CHARACTER = 1
const val VIEW_TAPE_LOAD_MORE = 0


fun mapperPeoplesEntityToPeoplesItemView(charactersEntity: CharactersEntity): CharacterItemView {
    return CharacterItemView(
        charactersEntity.id,
        charactersEntity.homeWorld,
        charactersEntity.gender,
        charactersEntity.skinColor,
        charactersEntity.edited,
        charactersEntity.created,
        charactersEntity.mass,
        charactersEntity.url,
        charactersEntity.hairColor,
        charactersEntity.birthYear,
        charactersEntity.eyeColor,
        charactersEntity.name,
        charactersEntity.height,
        charactersEntity.viewTape,
        charactersEntity.nextPage
    )
}

fun convertPogoToEntity(resultsItem: ResultsItem): CharactersEntity {
    return CharactersEntity(
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


