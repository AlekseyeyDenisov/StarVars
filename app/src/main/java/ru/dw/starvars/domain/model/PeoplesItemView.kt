package ru.dw.starvars.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.dw.starvars.utils.Utils

@Parcelize
data class PeoplesItemView(
    val id:Long,
    val homeWorld: String? = null,
    val gender: String? = null,
    val skinColor: String? = null,
    val edited: String? = null,
    val created: String? = null,
    val mass: String? = null,
    val url:  String? = null,
    val hairColor: String? = null,
    val birthYear: String? = null,
    val eyeColor: String? = null,
    val name: String,
    val height: String? = null,
    var viewTape: Int = Utils.VIEW_TAPE_CHARACTER,
    var nextPage:String? = null

): Parcelable

