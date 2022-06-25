package ru.dw.starvars.data.repositories.details

import ru.dw.starvars.data.retrofit.RetrofitApiStarWars
import ru.dw.starvars.data.retrofit.model.PlanetsPojo

interface ApiRetrofitDetailsInterface {
    fun getPlanetRequestUrl(
        url: String,
        responseListCallBackRetrofit: RetrofitApiStarWars.GenericCallBackRetrofit<PlanetsPojo>
    )
}