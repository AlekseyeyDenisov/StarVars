package ru.dw.starvars.data.room


import androidx.lifecycle.LiveData
import ru.dw.starvars.domain.model.PeoplesItemView
import ru.dw.starvars.utils.*

class HelperRoomPeople(
    private val peoplesDao:PeoplesDao,
    private val attributesDao: AttributesDao
) {

    private fun update(peoplesEntity: PeoplesEntity) = peoplesDao.update(peoplesEntity)

    private fun getPeople(name:String):PeoplesEntity = peoplesDao.gelItem(name)

    fun getAllPeoples(): List<PeoplesEntity> = peoplesDao.gelAll()

    private fun insertPeople(peoplesEntity: PeoplesEntity) = peoplesDao.insert(peoplesEntity)

    private fun  insertAttr(attributesEntity: AttributesEntity) = attributesDao.insert(attributesEntity)

    private fun deleteAttr(idPeoples: Long) = attributesDao.deleteAttr(idPeoples)


     fun insertUpdateDatabase(listPeoplesItemView: List<PeoplesItemView>) {
        Thread {
            listPeoplesItemView.forEach { item ->
                if (item.viewTape == PeoplesItemView.VIEW_TAPE_CHARACTER) {
                    val isHave = item.name?.let { getPeople(it) }
                    if (isHave != null) {
                        update(mapperItemViewToPeoplesEntity(item)).toLong()
                        deleteAttr(isHave.id)
                        insertAttr(isHave.id, item)
                    } else {
                        //val idPeoples = insertPeople(mapperItemViewToPeoplesEntity(item))
                       // insertAttr(idPeoples, item)
                    }
                }

            }

        }.start()
    }

    private fun insertAttr(idPeoples: Long, item: PeoplesItemView) {
        insertAttrDB(item.films as List<String>, idPeoples, CONSTANT_ATTRIBUTE_FILMS)

        insertAttrDB(item.species as List<String>, idPeoples, CONSTANT_ATTRIBUTE_SPECIES)

        insertAttrDB(item.vehicles as List<String>, idPeoples, CONSTANT_ATTRIBUTE_VEHICLES)

        insertAttrDB(item.starships as List<String>, idPeoples, CONSTANT_ATTRIBUTE_STARSHIPS)

    }

    private fun insertAttrDB(list: List<String>, idPeoples: Long, constantAttr: String) {
        if (list.isNotEmpty()) {
            list.forEach { url ->
                val attributesEntity = AttributesEntity(0, idPeoples, constantAttr, url)
                insertAttr(attributesEntity)
            }
        }
    }


}