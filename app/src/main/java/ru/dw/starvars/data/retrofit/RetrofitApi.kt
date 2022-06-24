package ru.dw.starvars.data.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url
import ru.dw.starvars.domain.model.PeoplesListResponsePojo


interface RetrofitApi {
    @GET
    fun getUrlResponse(@Url url: String): Call<PeoplesListResponsePojo>
}