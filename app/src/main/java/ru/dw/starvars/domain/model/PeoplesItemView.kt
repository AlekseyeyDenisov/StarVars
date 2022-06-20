package ru.dw.starvars.domain.model

data class PeoplesItemView(
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
    val height: String? = null,
    var viewTape: Int = VIEW_TAPE_CHARACTER,
    val nextPage:String? = null

)
{
    companion object {
        const val VIEW_TAPE_CHARACTER = 1
        const val VIEW_TAPE_LOAD_MORE = 0
    }
}
