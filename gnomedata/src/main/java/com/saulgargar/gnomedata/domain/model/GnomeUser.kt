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
    val friends: List<String>)

fun GnomeUser.toDomain(): Gnome {
    val gnome = Gnome()
    gnome.id =  id
    gnome.name =  name
    gnome.thumbNail =  thumbNail
    gnome.age =  age
    gnome.weight =  weight
    gnome.hairColor =  hairColor
    gnome.height =  height
    gnome.professions =  professions
    gnome.friends =  friends

    return gnome
}





