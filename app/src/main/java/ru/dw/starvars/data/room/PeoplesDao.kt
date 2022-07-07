package ru.dw.starvars.data.room


import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PeoplesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(peoplesEntity: PeopleDbModel): Long

    @Update
    fun update(peoplesEntity: PeopleDbModel): Int

    @Query("SELECT * FROM peoples ")
    fun getAll(): LiveData<List<PeopleDbModel>>

    @Query("DELETE  FROM peoples WHERE viewTape =:viewTape")
    fun deleteNextPage(viewTape:Int)

    @Query("DELETE  FROM peoples")
    fun deleteAll():Int

}

@Dao
interface ValuePlanetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(valueAttributesEntity: ValueAttrEntity): Long


    @Query("DELETE  FROM value_attributes")
    fun deleteAllAttr():Int

    @Query("SELECT * FROM value_attributes ")
    fun getAll(): List<ValueAttrEntity>

    @Query("SELECT * FROM value_attributes WHERE url =:url")
    fun getValuePlanet(url:String):ValueAttrEntity


}