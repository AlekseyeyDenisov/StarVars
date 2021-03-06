package ru.dw.starvars.data.room


import androidx.lifecycle.LiveData
import androidx.room.*
import ru.dw.starvars.data.room.entity.PeoplesEntity
import ru.dw.starvars.data.room.entity.ValueAttrEntity

@Dao
interface PeoplesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(peoplesEntity: PeoplesEntity): Long

    @Update
    fun update(peoplesEntity: PeoplesEntity): Int

    @Query("SELECT * FROM peoples ")
    fun getAll(): LiveData<List<PeoplesEntity>>

    @Query("DELETE  FROM peoples WHERE viewTape =:viewTape")
    fun deleteNextPage(viewTape:Int)

    @Query("DELETE  FROM peoples")
    fun deleteAll():Int

}

@Dao
interface ValueAttrDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(valueAttributesEntity: ValueAttrEntity): Long


    @Query("DELETE  FROM value_attributes")
    fun deleteAllAttr():Int

    @Query("SELECT * FROM value_attributes ")
    fun getAll(): List<ValueAttrEntity>

    @Query("SELECT * FROM value_attributes WHERE url =:url")
    fun getValueAttr(url:String):ValueAttrEntity


}