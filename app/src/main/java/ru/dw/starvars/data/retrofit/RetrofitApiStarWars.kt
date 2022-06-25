package ru.dw.starvars.data.retrofit


import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.dw.starvars.data.repositories.ApiStarWarsRetrofit
import ru.dw.starvars.domain.model.PeoplesListResponsePojo
import ru.dw.starvars.utils.BASE_URL



object RetrofitApiStarWars : ApiStarWarsRetrofit {
    private val retrofit: Retrofit = initRetrofit()

    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        }.build()
    }


    override fun getRequestUrl(url: String, responseCallBackRetrofit: ResponseCallBackRetrofit) {
        retrofit
            .create(RetrofitApi::class.java)
            .getUrlResponse(url)
            .enqueue(
                object : Callback<PeoplesListResponsePojo> {
                    override fun onResponse(
                        call: Call<PeoplesListResponsePojo>,
                        response: Response<PeoplesListResponsePojo>
                    ) {
                        if (response.isSuccessful) {
                            response.body()
                                ?.let { pogo ->
                                    responseCallBackRetrofit.success(pogo)
                                }

                        } else {
                            responseCallBackRetrofit.error(response.message())
                            //responseCallBackViewModel.error("Что-то пошло не так")

                        }
                    }

                    override fun onFailure(call: Call<PeoplesListResponsePojo>, t: Throwable) {
                        t.message?.let { responseCallBackRetrofit.error(it) }

                    }

                }
            )
    }


    interface ResponseCallBackRetrofit {
        fun success(pogo: PeoplesListResponsePojo)
        fun error(error: String)
    }


}