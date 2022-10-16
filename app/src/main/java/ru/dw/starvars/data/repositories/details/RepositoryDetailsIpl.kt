package ru.dw.starvars.data.repositories.details


import ru.dw.starvars.data.api.ApiService
import ru.dw.starvars.data.api.model.PlanetsPojo
import ru.dw.starvars.data.database.AppDataBase
import ru.dw.starvars.data.database.entity.ValueAttrEntity
import ru.dw.starvars.domain.RepositoryDetails
import ru.dw.starvars.pressentation.view.details.DetailsFragment.Companion.CONSTANT_ATTRIBUTE_HOME_WORLD
import javax.inject.Inject


class RepositoryDetailsIpl @Inject constructor(
    private val dbDao: AppDataBase,
    private val dataApi : ApiService
) : RepositoryDetails {




    override fun getAllValueAttr(): List<ValueAttrEntity> = dbDao.valueDao().getAll()

    override fun getValueAttr(url: String): ValueAttrEntity {
        return dbDao.valueDao().getValueAttr(url)
    }

    override fun getRequestUrl(url: String, attr: String, name: (String) -> Unit) {
        when (attr) {
            CONSTANT_ATTRIBUTE_HOME_WORLD -> getIpiPlanet(url, name)
        }
    }


    private fun insertValueAttr(valueAttributesEntity: ValueAttrEntity) {
        dbDao.valueDao().insert(valueAttributesEntity)
    }

    private fun getIpiPlanet(url: String, name: (String) -> Unit) {
        dataApi.getPlanetRequestUrl(
            url,
            object : ApiService.CallBackRetrofit<PlanetsPojo> {
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
