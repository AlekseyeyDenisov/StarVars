package ru.dw.starvars.data.retrofit

import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.dw.starvars.domain.model.PeoplesItemView
import ru.dw.starvars.domain.model.PeoplesItemView.Companion.VIEW_TAPE_LOAD_MORE
import ru.dw.starvars.domain.model.PeoplesListResponsePojo
import ru.dw.starvars.presenter.list.ListPeoplesViewModel
import ru.dw.utils.BASE_URL
import ru.dw.utils.mapperPeoplesListPojoToPeoplesItemView

object RetrofitApiStarWars {
    private val retrofit: Retrofit = initRetrofit()



    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        }.build()
    }

    fun getListPeoples(url: String, responseCallBack: ListPeoplesViewModel.ResponseCallBack) {
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
                                ?.let { createListPeopleView(it) }
                                ?.let { responseCallBack.success(it) }

                        } else {
                            responseCallBack.error(response.message())

                        }
                    }
                    override fun onFailure(call: Call<PeoplesListResponsePojo>, t: Throwable) {
                        t.message?.let { responseCallBack.error(it) }

                    }

                }
            )

    }

    fun createListPeopleView(
        peoplesListResponsePojo: PeoplesListResponsePojo
    ): List<PeoplesItemView> {
        val listPeoplesItemView = mutableListOf<PeoplesItemView>()
        peoplesListResponsePojo.results?.forEach { resultItem ->
            resultItem?.let { mapperPeoplesListPojoToPeoplesItemView(it) }
                ?.let { listPeoplesItemView.add(it) }
        }
        if (peoplesListResponsePojo.next != null) {
            listPeoplesItemView.add(
                PeoplesItemView(
                    viewTape = VIEW_TAPE_LOAD_MORE,
                    nextPage = peoplesListResponsePojo.next
                )
            )
        }
        return listPeoplesItemView
    }
}