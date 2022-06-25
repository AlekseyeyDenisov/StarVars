package ru.dw.starvars.data.repositories.list

import ru.dw.starvars.data.retrofit.RetrofitApiStarWars
import ru.dw.starvars.data.retrofit.model.PlanetsPojo

interface ApiRetrofitListInterface {
    fun getListRequestUrl(
        url: String,
        responseListCallBackRetrofit: RetrofitApiStarWars.ResponseListCallBackRetrofit
    )

}