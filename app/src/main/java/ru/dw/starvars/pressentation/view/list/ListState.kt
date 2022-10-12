package ru.dw.starvars.pressentation.view.list

import ru.dw.starvars.domain.model.CharacterItemView

sealed class ListState{
    object Loading : ListState()
    data class Success(val chapterList:List<CharacterItemView>): ListState()
    data class Error(val error:String): ListState()
}
