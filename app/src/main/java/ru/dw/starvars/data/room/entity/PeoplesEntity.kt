package ru.dw.starvars.data.room.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import ru.dw.starvars.utils.CONSTANT_TABLE_PEOPLES
import ru.dw.starvars.utils.VIEW_TAPE_CHARACTER

@Parcelize
@Entity(tableName = CONSTANT_TABLE_PEOPLES)
data class PeoplesEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val homeWorld: String? = null,
    val gender: String? = null,
    val skinColor: String? = null,
    val edited: String? = null,
    val created: String? = null,
    val mass: String? = null,
    val url: String? = null,
    val hairColor: String? = null,
    val birthYear: String? = null,
    val eyeColor: String? = null,
    val name: String = "@@@",
    val height: String? = null,
    var viewTape: Int = VIEW_TAPE_CHARACTER,
    val nextPage:String? = null
):Parcelable

@Entity(tableName = "attributes")
data class AttributesEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val idPeoples:Long,
    val attr:String,
    val url: String
)

@Entity(tableName = "value_attributes")
data class ValueAttrEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val url: String,
    val name: String
)
