package ru.dw.starvars.data.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url
import ru.dw.domain.PeoplesListResponsePojo


interface RetrofitApi {


    @GET
    fun getUrlResponse(@Url url: String): Call<PeoplesListResponsePojo>
}