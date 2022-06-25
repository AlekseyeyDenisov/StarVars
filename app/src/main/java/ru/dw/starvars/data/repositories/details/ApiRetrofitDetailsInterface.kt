package ru.dw.starvars.data.repositories.details

import ru.dw.starvars.data.retrofit.RetrofitApiStarWars

interface ApiRetrofitDetailsInterface {
    fun getPlanetRequestUrl(
        url: String,
        responseListCallBackRetrofit: RetrofitApiStarWars.ResponseListCallBackRetrofit
    )
}