package ru.dw.starvars.data.repositories

import androidx.lifecycle.LiveData
import ru.dw.starvars.data.retrofit.RetrofitApiStarWars
import ru.dw.starvars.data.retrofit.model.PeoplesListResponsePojo
import ru.dw.starvars.data.retrofit.model.PlanetsPojo
import ru.dw.starvars.data.room.PeopleDbModel
import ru.dw.starvars.data.room.ValueAttrEntity
import ru.dw.starvars.domain.repository.RepositoryDetails
import ru.dw.starvars.domain.repository.RepositoryList
import ru.dw.starvars.presentation.viewmodel.list.ListPeoplesViewModel


class RepositoryIpl(
    private val dataApi: ApiBase,
    private val dataRoom: LocalBase
) : RepositoryList, RepositoryDetails {


    override fun getAllPeoples(): LiveData<List<PeopleDbModel>> = dataRoom.getAllPeoples()

    override fun insertDatabasePeoples(pogo: PeoplesListResponsePojo) {
        dataRoom.insertDatabasePeoples(pogo)
    }

    override fun refresh() = dataRoom.refresh()

    override fun getRequestListUrl(
        url: String,
        responseCallBack: ListPeoplesViewModel.ResponseCallBackViewModel
    ) {
        dataApi.getListRequestUrl(
            url,
            object : RetrofitApiStarWars.CallBackRetrofit<PeoplesListResponsePojo> {

                override fun success(pogo: PeoplesListResponsePojo) {
                    insertDatabasePeoples(pogo)
                }

                override fun error(error: String) {
                    responseCallBack.error(error)
                }
            })
    }

    override fun getAllValueAttr(): List<ValueAttrEntity> = dataRoom.getAllValueAttr()

    override fun getRequestPlanetUrl(url: String, name: (String) -> Unit) = getIpiPlanet(url, name)



    override fun getValuePlanet(url: String): ValueAttrEntity = dataRoom.getValuePlanet(url)

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

}
