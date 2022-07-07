package ru.dw.starvars.data.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url
import ru.dw.starvars.data.retrofit.model.PeoplesListResponsePojo
import ru.dw.starvars.data.retrofit.model.PlanetsPojo


interface RetrofitApi {
    @GET
    fun getUrlPeoplesResponse(@Url url: String): Call<PeoplesListResponsePojo>

    @GET
    fun getUrlPlanetResponse(@Url url: String): Call<PlanetsPojo>
}