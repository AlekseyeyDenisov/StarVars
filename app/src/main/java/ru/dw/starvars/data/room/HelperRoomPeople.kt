package ru.dw.starvars.data.room

import androidx.lifecycle.LiveData

class HelperRoomPeople(private val db:PeoplesDao) {

    fun getAllPeoples(): LiveData<List<PeoplesEntity>> = db.gelAll()

    fun setPeople(peoplesEntity: PeoplesEntity) = db.insert(peoplesEntity)
}