package ru.dw.starvars.data.repositories.details


import ru.dw.starvars.data.repositories.list.ApiRetrofitListInterface
import ru.dw.starvars.data.repositories.list.DataBaseLocal
import ru.dw.starvars.data.retrofit.RetrofitApiStarWars
import ru.dw.starvars.data.retrofit.model.PlanetsPojo
import ru.dw.starvars.data.room.HelperRoomPeople


class RepositoryDetailsIpl : ApiRetrofitDetailsInterface {
    private val dataApi: ApiRetrofitListInterface = RetrofitApiStarWars
    private val dataRoom: DataBaseLocal = HelperRoomPeople()


    override fun getPlanetRequestUrl(
        url: String,
        responseListCallBackRetrofit: RetrofitApiStarWars.GenericCallBackRetrofit<PlanetsPojo>
    ) {
        TODO("Not yet implemented")
    }


}
