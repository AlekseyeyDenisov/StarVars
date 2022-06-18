package ru.dw.starvars.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PeoplesDao {

    @Insert
    fun insert(peoplesEntity: PeoplesEntity)

    @Query("SELECT * FROM peoples ")
    fun gelAll():LiveData<List<PeoplesEntity>>
}