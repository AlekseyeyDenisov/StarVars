package ru.dw.starvars.data.retrofit


import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.dw.starvars.data.repositories.list.ApiRetrofitListInterface
import ru.dw.starvars.data.retrofit.model.PlanetsPojo
import ru.dw.starvars.domain.model.PeoplesListResponsePojo
import ru.dw.starvars.utils.BASE_URL


object RetrofitApiStarWars : ApiRetrofitListInterface {
    private val retrofit: RetrofitApi = initRetrofit()
    private val callBackUrl = CallBackUrl<PlanetsPojo>()

    private fun initRetrofit(): RetrofitApi {
        return Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        }.build()
            .create(RetrofitApi::class.java)
    }


    override fun getListRequestUrl(
        url: String,
        responseListCallBackRetrofit: ResponseListCallBackRetrofit
    ) {
        retrofit
            .getUrlPeoplesResponse(url)
            .enqueue(
                object : Callback<PeoplesListResponsePojo> {
                    override fun onResponse(
                        call: Call<PeoplesListResponsePojo>,
                        response: Response<PeoplesListResponsePojo>
                    ) {
                        if (response.isSuccessful) {
                            response.body()
                                ?.let { pogo ->
                                    responseListCallBackRetrofit.success(pogo)
                                }

                        } else {
                            responseListCallBackRetrofit.error(response.message())
                            //responseCallBackViewModel.error("Что-то пошло не так")

                        }
                    }

                    override fun onFailure(call: Call<PeoplesListResponsePojo>, t: Throwable) {
                        t.message?.let { responseListCallBackRetrofit.error(it) }

                    }

                }
            )
    }

    fun getPlanetRequestUrl(
        url: String,
        responseBackRetrofit: GenericCallBackRetrofit<PlanetsPojo>
    ) {
        retrofit
            .getUrlPlanetResponse(url)
            .enqueue(
                callBackUrl.callback(responseBackRetrofit)
            )
    }


    interface ResponseListCallBackRetrofit {
        fun success(pogo: PeoplesListResponsePojo)
        fun error(error: String)
    }

    interface GenericCallBackRetrofit<T> {
        fun success(pogo: T)
        fun error(error: String)
    }

}