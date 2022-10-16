package ru.dw.starvars.data.retrofit


import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.dw.starvars.data.repositories.list.ApiRetrofitListInterface
import ru.dw.starvars.data.retrofit.model.PlanetsPojo
import ru.dw.starvars.domain.model.CharactersListResponsePojo
import javax.inject.Inject


class RetrofitApiStarWars @Inject constructor() : ApiRetrofitListInterface {
    private val retrofit: RetrofitApi = initRetrofit()
    companion object {
        private const val BASE_URL = "https://swapi.dev/api/"
    }


    private fun initRetrofit(): RetrofitApi {
        return Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        }.build()
            .create(RetrofitApi::class.java)
    }

    override fun getListRequestUrl(
        url: String,
        genericCallBackRetrofit: CallBackRetrofit<CharactersListResponsePojo>
    ) {
        val callBackUrl = CallBackUrl<CharactersListResponsePojo>()
        retrofit
            .getUrlChaptersResponse(url)
            .enqueue(
                callBackUrl.callback(genericCallBackRetrofit)
            )
    }

    fun getPlanetRequestUrl(
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