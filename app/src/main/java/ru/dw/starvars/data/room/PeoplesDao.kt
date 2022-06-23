package ru.dw.starvars.data.room


import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PeoplesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(peoplesEntity: PeoplesEntity):Long

    @Update
    fun update(peoplesEntity: PeoplesEntity):Int

    @Query("SELECT * FROM peoples  WHERE name =:name")
    fun gelItem(name:String):PeoplesEntity


    @Query("SELECT * FROM peoples ")
    fun gelAll():List<PeoplesEntity>
}
@Dao
interface AttributesDao {

    @Insert
    fun insert(attributesEntity: AttributesEntity):Long


    @Query("SELECT * FROM attributes WHERE idPeoples = :idPeoples")
    fun gelAttr(idPeoples:Int):List<AttributesEntity>

    @Query("DELETE  FROM attributes WHERE idPeoples = :idPeoples")
    fun deleteAttr(idPeoples:Long)



}
