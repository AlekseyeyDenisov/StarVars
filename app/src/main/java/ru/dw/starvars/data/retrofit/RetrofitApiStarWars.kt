package ru.dw.starvars.data.retrofit


import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.dw.starvars.data.repositories.ApiBase
import ru.dw.starvars.data.retrofit.model.PeoplesListResponsePojo
import ru.dw.starvars.data.retrofit.model.PlanetsPojo
import ru.dw.starvars.utils.Utils

class RetrofitApiStarWars : ApiBase {
    private val retrofit: RetrofitApi = initRetrofit()

    private fun initRetrofit(): RetrofitApi {
        return Retrofit.Builder().apply {
            baseUrl(Utils.BASE_URL)
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        }.build()
            .create(RetrofitApi::class.java)
    }

    override fun getListRequestUrl(
        url: String,
        genericCallBackRetrofit: CallBackRetrofit<PeoplesListResponsePojo>
    ) {
        val callBackUrl = CallBackUrl<PeoplesListResponsePojo>()
        retrofit
            .getUrlPeoplesResponse(url)
            .enqueue(
                callBackUrl.callback(genericCallBackRetrofit)
            )
    }

    override fun getPlanetRequestUrl(
        url: String,
        genericCallBackRetrofit: CallBackRetrofit<PlanetsPojo>
    ) {
        val callBackUrl = CallBackUrl<PlanetsPojo>()
        retrofit
            .getUrlPlanetResponse(url)
            .enqueue(
                callBackUrl.callback(genericCallBackRetrofit)
            )

    }


    interface CallBackRetrofit<T> {
        fun success(pogo: T)
        fun error(error: String)
    }

}