package ru.dw.starvars.data.repositories

import ru.dw.starvars.data.retrofit.RetrofitApiStarWars
import ru.dw.starvars.data.retrofit.model.PeoplesListResponsePojo

interface ApiRetrofitListInterface {
    fun getListRequestUrl(
        url: String,
        genericCallBackRetrofit: RetrofitApiStarWars.CallBackRetrofit<PeoplesListResponsePojo>
    )

}