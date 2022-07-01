package ru.dw.starvars.data.repositories

import androidx.lifecycle.LiveData
import ru.dw.starvars.data.retrofit.RetrofitApiStarWars
import ru.dw.starvars.data.retrofit.model.PlanetsPojo
import ru.dw.starvars.data.room.HelperRoomPeople
import ru.dw.starvars.data.room.entity.PeoplesEntity
import ru.dw.starvars.data.room.entity.ValueAttrEntity

import ru.dw.starvars.data.retrofit.model.PeoplesListResponsePojo
import ru.dw.starvars.presentation.viewmodel.list.ListPeoplesViewModel
import ru.dw.starvars.domain.repository.RepositoryList
import ru.dw.starvars.domain.repository.RepositoryDetails


object RepositoryIpl : RepositoryList, RepositoryDetails {
    private val dataApi = RetrofitApiStarWars
    private val dataRoom = HelperRoomPeople()

    override fun getAllPeoples(): LiveData<List<PeoplesEntity>> = dataRoom.getAllPeoples()

    override fun insertDatabasePeoples(pogo: PeoplesListResponsePojo) {
        dataRoom.insertDatabasePeoples(pogo)
    }

    override fun refresh() = dataRoom.refresh()

    override fun getRequestUrl(
        url: String,
        responseCallBack: ListPeoplesViewModel.ResponseCallBackViewModel
    ) {
        dataApi.getListRequestUrl(url, object : RetrofitApiStarWars.CallBackRetrofit<PeoplesListResponsePojo> {

            override fun success(pogo: PeoplesListResponsePojo) {
                insertDatabasePeoples(pogo)
            }

            override fun error(error: String) {
                responseCallBack.error(error)
            }
        })
    }

    override fun getRequestUrl(url: String, name: (String) -> Unit) {
        getIpiPlanet(url, name)

    }

    private fun getIpiPlanet(url: String, name: (String) -> Unit) {
        dataApi.getPlanetRequestUrl(
            url,
            object : RetrofitApiStarWars.CallBackRetrofit<PlanetsPojo> {
                override fun success(pogo: PlanetsPojo) {

                    pogo.name?.let { valueName ->
                        name(valueName)
                        val entity = ValueAttrEntity(0, url, valueName)
                        Thread {
                            dataRoom.insertValueAttr(entity)
                        }.start()
                    }
                }

                override fun error(error: String) {
                    name(error)
                }
            })
    }

    override fun getAllValueAttr(): List<ValueAttrEntity> = dataRoom.getAllValueAttr()

    override fun getValueAttr(url: String): ValueAttrEntity = dataRoom.getValueAttr(url)

}
