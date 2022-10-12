package ru.dw.starvars.data.mapper

import ru.dw.starvars.data.room.entity.CharactersDBModel
import ru.dw.starvars.domain.model.CharacterItemView
import ru.dw.starvars.domain.model.ResultsItem
import ru.dw.starvars.utils.VIEW_TAPE_CHARACTER

class DataMapper {
    fun mapperPeoplesDBModelToPeoplesItemView(
        charactersDBModel: CharactersDBModel
    ): CharacterItemView {
        return CharacterItemView(
            charactersDBModel.id,
            charactersDBModel.homeWorld,
            charactersDBModel.gender,
            charactersDBModel.skinColor,
            charactersDBModel.edited,
            charactersDBModel.created,
            charactersDBModel.mass,
            charactersDBModel.url,
            charactersDBModel.hairColor,
            charactersDBModel.birthYear,
            charactersDBModel.eyeColor,
            charactersDBModel.name,
            charactersDBModel.height,
            charactersDBModel.viewTape,
            charactersDBModel.nextPage
        )
    }

    fun mapperPogoToEntity(resultsItem: ResultsItem): CharactersDBModel {
        return CharactersDBModel(
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