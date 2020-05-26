package com.saulgargar.gnomedata.data.datasource.remote.model

import com.saulgargar.gnomedata.domain.model.GnomeUser
import com.squareup.moshi.Json
import com.squareup.moshi.Types

class GnomeInfoResponse (
    @field:Json(name = "Brastlewark")
    val brastlewark: List<GnomeInfoItemResponse>)

class GnomeInfoItemResponse (
    @field:Json(name = "id")
    val id: Int,

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "thumbnail")
    val thumbNail: String,

    @field:Json(name = "age")
    val age: Int,

    @field:Json(name = "weight")
    val weight: Float,

    @field:Json(name = "height")
    val height: Float,

    @field:Json(name = "hair_color")
    val hairColor: String,

    @field:Json(name = "professions")
    val professions: List<String>,

    @field:Json(name = "friends")
    val friends: List<String>)

fun GnomeInfoResponse.toDomain() : List<GnomeUser>{
    val gnomeList = mutableListOf<GnomeUser>()
    for (gnome in brastlewark){
        gnomeList.add(GnomeUser(
            id = gnome.id,
            name = gnome.name,
            thumbNail = gnome.thumbNail,
            age = gnome.age,
            weight = gnome.weight,
            height = gnome.height,
            hairColor = gnome.hairColor,
            professions = gnome.professions,
            friends = gnome.friends))
    }
    return gnomeList
}