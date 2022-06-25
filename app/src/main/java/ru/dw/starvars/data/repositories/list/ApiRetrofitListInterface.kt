package ru.dw.starvars.data.repositories.list

import ru.dw.starvars.data.retrofit.RetrofitApiStarWars
import ru.dw.starvars.domain.model.PeoplesListResponsePojo

interface ApiRetrofitListInterface {
    fun getListRequestUrl(
        url: String,
        genericCallBackRetrofit: RetrofitApiStarWars.GenericCallBackRetrofit<PeoplesListResponsePojo>
    )

}