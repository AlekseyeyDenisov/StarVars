package ru.dw.starvars.data.room


import androidx.lifecycle.LiveData
import androidx.room.*
import ru.dw.starvars.data.room.entity.CharactersDBModel
import ru.dw.starvars.data.room.entity.ValueAttrEntity

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(charactersDBModel: CharactersDBModel): Long

    @Update
    fun update(charactersDBModel: CharactersDBModel): Int

    @Query("SELECT * FROM chapters  ")
    fun getAll(): LiveData<List<CharactersDBModel>>

    @Query("DELETE  FROM chapters WHERE viewTape =:viewTape")
    fun deleteNextPage(viewTape:Int)

    @Query("DELETE  FROM chapters")
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