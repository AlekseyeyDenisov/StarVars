package ru.dw.starvars.view.list

import ru.dw.starvars.domain.model.PeoplesItemView

sealed class ListState{
    object Loading : ListState()
    data class Success(val peopleList:List<PeoplesItemView>): ListState()
    data class Error(val error:String): ListState()
}
