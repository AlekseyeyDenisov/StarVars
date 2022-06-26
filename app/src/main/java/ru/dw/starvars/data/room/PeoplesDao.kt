package ru.dw.starvars.data.room


import androidx.lifecycle.LiveData
import androidx.room.*
import ru.dw.starvars.data.room.entity.AttributesEntity
import ru.dw.starvars.data.room.entity.PeoplesEntity

@Dao
interface PeoplesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(peoplesEntity: PeoplesEntity): Long

    @Update
    fun update(peoplesEntity: PeoplesEntity): Int

    @Query("SELECT * FROM peoples  WHERE name =:nameItem")
    fun gelItem(nameItem: String): PeoplesEntity


    @Query("SELECT * FROM peoples ")
    fun gelAll(): LiveData<List<PeoplesEntity>>

    @Query("DELETE  FROM peoples WHERE viewTape =:viewTape")
    fun deleteNextPage(viewTape:Int)

    @Query("DELETE  FROM peoples")
    fun deleteAll():Int

}

@Dao
interface AttributesDao {

    @Insert
    fun insert(attributesEntity: AttributesEntity): Long


    @Query("SELECT * FROM attributes WHERE idPeoples = :idPeoples")
    fun gelAttr(idPeoples: Long): List<AttributesEntity>

    @Query("DELETE  FROM attributes")
    fun deleteAllAttr():Int


}

@Dao
interface ValueAttrDao {

    @Insert
    fun insert(valueAttributesEntity: AttributesEntity): Long


//    @Query("SELECT * FROM attributes WHERE idPeoples = :idPeoples")
//    fun gelAttr(idPeoples: Long): List<AttributesEntity>

    @Query("DELETE  FROM value_attributes")
    fun deleteAllAttr():Int


}