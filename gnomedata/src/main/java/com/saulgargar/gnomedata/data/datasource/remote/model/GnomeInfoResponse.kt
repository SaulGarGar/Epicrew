package com.saulgargar.gnomedata.data.datasource.remote.model

import com.squareup.moshi.Json

class GnomeInfoResponse (
    @field:Json(name = "Brastlewark")
    val brastlewark: ArrayList<GnomeInfoItemResponse>)

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
    val hairColor: Int,

    @field:Json(name = "professions")
    val professions: ArrayList<String>,

    @field:Json(name = "friends")
    val friends: ArrayList<String>)