package ru.dw.starvars.data.repositories

import ru.dw.starvars.domain.model.PeoplesItemView


sealed class ListStateResponse {
    object Loading : ListStateResponse()
    data class Success(val weatherList:List<PeoplesItemView>): ListStateResponse()
    data class Error(val error:String): ListStateResponse()
}