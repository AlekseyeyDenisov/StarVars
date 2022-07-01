package ru.dw.starvars.data.room.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import ru.dw.starvars.utils.Utils


@Parcelize
@Entity(tableName = Utils.CONSTANT_TABLE_PEOPLES)
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
    var viewTape: Int = Utils.VIEW_TAPE_CHARACTER,
    val nextPage:String? = null
):Parcelable

@Entity(tableName = "value_attributes")
data class ValueAttrEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val url: String,
    val name: String
)
