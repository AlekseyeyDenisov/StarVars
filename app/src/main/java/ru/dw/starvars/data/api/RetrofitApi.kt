package ru.dw.starvars.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url
import ru.dw.starvars.data.api.model.PlanetsPojo
import ru.dw.starvars.domain.model.CharactersListResponsePojo


interface RetrofitApi {
    @GET
    fun getUrlChaptersResponse(@Url url: String): Call<CharactersListResponsePojo>

    @GET
    fun getUrlPlanetResponse(@Url url: String): Call<PlanetsPojo>
}