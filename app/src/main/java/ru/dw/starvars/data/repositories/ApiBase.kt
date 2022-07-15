package ru.dw.starvars.data.repositories

import ru.dw.starvars.data.network.RetrofitApiStarWars
import ru.dw.starvars.data.network.model.PeoplesListResponsePojo
import ru.dw.starvars.data.network.model.PlanetsPojo

interface ApiBase {
    fun getListRequestUrl(
        url: String,
        genericCallBackRetrofit: RetrofitApiStarWars.CallBackRetrofit<PeoplesListResponsePojo>
    )
    fun getPlanetRequestUrl(
        url: String,
        genericCallBackRetrofit: RetrofitApiStarWars.CallBackRetrofit<PlanetsPojo>
    )
}