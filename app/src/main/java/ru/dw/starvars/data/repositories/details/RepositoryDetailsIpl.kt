package ru.dw.starvars.data.repositories.details


import ru.dw.starvars.data.retrofit.RetrofitApiStarWars
import ru.dw.starvars.data.retrofit.model.PlanetsPojo
import ru.dw.starvars.data.room.HelperRooDetails
import ru.dw.starvars.data.room.entity.ValueAttrEntity
import ru.dw.starvars.domain.RepositoryDetails
import ru.dw.starvars.utils.CONSTANT_ATTRIBUTE_HOME_WORLD


class RepositoryDetailsIpl : RepositoryDetails {
    private val dataApi = RetrofitApiStarWars
    private val dataRoom: DataBaseDetailsLocal = HelperRooDetails()

    override fun getRequestUrl(url: String, attr: String, name: (String) -> Unit) {
        when (attr) {
            CONSTANT_ATTRIBUTE_HOME_WORLD -> getIpiPlanet(url, name)
        }
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
