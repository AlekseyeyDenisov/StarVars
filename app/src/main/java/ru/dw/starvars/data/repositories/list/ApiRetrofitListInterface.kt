package ru.dw.starvars.data.repositories.list

import ru.dw.starvars.data.api.ApiService
import ru.dw.starvars.domain.model.CharactersListResponsePojo

interface ApiRetrofitListInterface {
    fun getListRequestUrl(
        url: String,
        genericCallBackRetrofit: ApiService.CallBackRetrofit<CharactersListResponsePojo>
    )

}