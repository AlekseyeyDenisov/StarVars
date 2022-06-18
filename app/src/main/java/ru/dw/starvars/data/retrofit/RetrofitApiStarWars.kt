package ru.dw.starvars.data.retrofit

import android.util.Log
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.dw.domain.PeoplesListResponsePojo

object RetrofitApiStarWars {
    private val  retrofit:Retrofit = initRetrofit()
    private const val BASE_URL = "https://swapi.dev/api/"
    private const val PEOPLES_LIST_URL = "https://swapi.dev/api/people/"


    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        }.build()
    }

    fun getListPeoples(){
        retrofit
            .create(RetrofitApi::class.java)
            .getUrlResponse(PEOPLES_LIST_URL)
            .enqueue(
                object :Callback<PeoplesListResponsePojo>{
                    override fun onResponse(
                        call: Call<PeoplesListResponsePojo>,
                        response: Response<PeoplesListResponsePojo>
                    ) {
                        if (response.isSuccessful){
                            Log.d("@@@", "onResponse body: " + response.body())
                            Log.d("@@@", "onResponse message: " + response.message())
                        }else{
                            Log.d("@@@", "onResponse else message: " + response.message())
                            Log.d("@@@", "onResponse else body: " + response.body())
                        }
                    }

                    override fun onFailure(call: Call<PeoplesListResponsePojo>, t: Throwable) {
                        Log.d("@@@", "onFailure: ${t.message}")
                    }

                }
            )

    }
}