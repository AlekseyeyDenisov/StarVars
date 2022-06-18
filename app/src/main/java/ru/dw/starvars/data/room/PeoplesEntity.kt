package ru.dw.starvars.data.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "peoples")
data class PeoplesEntity(
    @PrimaryKey(autoGenerate = true)
    val films: List<String?>? = null,
    val homeWorld: String? = null,
    val gender: String? = null,
    val skinColor: String? = null,
    val edited: String? = null,
    val created: String? = null,
    val mass: String? = null,
    val vehicles: List<String?>? = null,
    val url: String? = null,
    val hairColor: String? = null,
    val birthYear: String? = null,
    val eyeColor: String? = null,
    val species: List<Any?>? = null,
    val starships: List<String?>? = null,
    val name: String? = null,
    val height: String? = null

):Parcelable
