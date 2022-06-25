package ru.dw.starvars.data.retrofit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CallBackUrl<T> {
    fun callback(responseGeneric: RetrofitApiStarWars.GenericCallBackRetrofit<T>): Callback<T> {
        return object : Callback<T> {
            override fun onResponse(
                call: Call<T>,
                response: Response<T>
            ) {
                if (response.isSuccessful) {
                    response.body()
                        ?.let { pogo ->
                            responseGeneric.success(pogo)
                        }

                } else {
                    responseGeneric.error(response.message())

                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                t.message?.let { responseGeneric.error(it) }

            }

        }
    }
}