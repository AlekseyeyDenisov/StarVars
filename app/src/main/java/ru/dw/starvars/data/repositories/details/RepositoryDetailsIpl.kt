package ru.dw.starvars.data.repositories.details


import ru.dw.starvars.MyApp
import ru.dw.starvars.data.retrofit.RetrofitApiStarWars
import ru.dw.starvars.data.retrofit.model.PlanetsPojo
import ru.dw.starvars.data.room.DBRoom
import ru.dw.starvars.data.room.entity.ValueAttrEntity
import ru.dw.starvars.domain.RepositoryDetails
import ru.dw.starvars.pressentation.view.details.DetailsFragment.Companion.CONSTANT_ATTRIBUTE_HOME_WORLD


class RepositoryDetailsIpl : RepositoryDetails {
    private val dataApi = RetrofitApiStarWars
    private val db: DBRoom = MyApp.dbRoom

    override fun getAllValueAttr(): List<ValueAttrEntity> = db.valueDao().getAll()

    override fun getValueAttr(url: String): ValueAttrEntity {
        return db.valueDao().getValueAttr(url)
    }

    override fun getRequestUrl(url: String, attr: String, name: (String) -> Unit) {
        when (attr) {
            CONSTANT_ATTRIBUTE_HOME_WORLD -> getIpiPlanet(url, name)
        }
    }


    private fun insertValueAttr(valueAttributesEntity: ValueAttrEntity) {
        db.valueDao().insert(valueAttributesEntity)
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
                            insertValueAttr(entity)
                        }.start()
                    }
                }

                override fun error(error: String) {
                    name(error)
                }
            })
    }



}
