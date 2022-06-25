package ru.dw.starvars.data.repositories.details


import ru.dw.starvars.MyApp
import ru.dw.starvars.data.repositories.list.ApiRetrofitListInterface
import ru.dw.starvars.data.repositories.list.DataBaseLocal
import ru.dw.starvars.data.retrofit.RetrofitApiStarWars



object RepositoryDetailsIpl : ApiRetrofitDetailsInterface {
    private val dataApi: ApiRetrofitListInterface = RetrofitApiStarWars
    private val dataRoom: DataBaseLocal = MyApp.getDBRoom()

    override fun getPlanetRequestUrl(
        url: String,
        responseListCallBackRetrofit: RetrofitApiStarWars.ResponseListCallBackRetrofit
    ) {
        TODO("Not yet implemented")
    }


}
