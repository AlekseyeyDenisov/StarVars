package ru.dw.starvars.data.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url
import ru.dw.starvars.data.network.model.PeoplesListResponsePojo
import ru.dw.starvars.data.network.model.PlanetsPojo


interface RetrofitApi {
    @GET
    fun getUrlPeoplesResponse(@Url url: String): Call<PeoplesListResponsePojo>

    @GET
    fun getUrlPlanetResponse(@Url url: String): Call<PlanetsPojo>
}