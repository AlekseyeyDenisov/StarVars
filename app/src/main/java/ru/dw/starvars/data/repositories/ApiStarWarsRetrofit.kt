package ru.dw.starvars.data.repositories

import ru.dw.starvars.data.retrofit.RetrofitApiStarWars

interface ApiStarWarsRetrofit {
    fun getRequestUrl(
        url: String,
        responseCallBackRetrofit: RetrofitApiStarWars.ResponseCallBackRetrofit
    )
}