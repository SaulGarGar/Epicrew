package com.saulgargar.gnomedata.domain.model

data class GnomeUser(
    val id: Int,
    val name: String,
    val thumbNail: String,
    val age: Int,
    val weight: Float,
    val height: Float,
    val hairColor: String,
    val professions: List<String>,
    val friends: List<String>
)
