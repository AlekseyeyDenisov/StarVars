package ru.dw.starvars.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import ru.dw.starvars.data.retrofit.RetrofitApiStarWars
import ru.dw.starvars.data.retrofit.model.PeoplesListResponsePojo
import ru.dw.starvars.data.retrofit.model.PlanetsPojo
import ru.dw.starvars.data.room.PeopleListMapper
import ru.dw.starvars.data.room.ValueAttrEntity
import ru.dw.starvars.domain.model.PeoplesItemView
import ru.dw.starvars.domain.repository.RepositoryDetails
import ru.dw.starvars.domain.repository.RepositoryList
import ru.dw.starvars.presentation.viewmodel.list.ListPeoplesViewModel
import kotlin.concurrent.thread


class RepositoryIpl(
    private val dataApi: ApiBase,
    private val dataRoom: LocalBase
) : RepositoryList, RepositoryDetails {
    private val mapper = PeopleListMapper()


    //    override fun getAllPeoples(): LiveData<List<PeoplesItemView>> =
//            MediatorLiveData<List<PeoplesItemView>>().apply {
//            addSource(dataRoom.getAllPeoples()) {
//                value = mapper.mapListDbModelToListEntity(it)
//            }
//        }
    override fun getAllPeoples(): LiveData<List<PeoplesItemView>> =
        Transformations.map(
            dataRoom.getAllPeoples()
        ) {
            mapper.mapListDbModelToListEntity(it)
        }

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
                    thread {
                        insertDatabasePeoples(pogo)
                    }
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
                        dataRoom.insertValueAttr(entity)

                    }
                }

                override fun error(error: String) {
                    name(error)
                }
            })
    }

}
