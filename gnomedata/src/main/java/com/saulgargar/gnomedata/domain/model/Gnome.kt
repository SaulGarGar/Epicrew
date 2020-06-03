package com.saulgargar.gnomedata.domain.model

import java.io.Serializable

class Gnome: Serializable {
    var id = 0
    var name = ""
    var thumbNail = ""
    var age = 0
    var weight = 0F
    var height = 0F
    var hairColor = ""
    var professions = listOf<String>()
    var friends = listOf<String>()
}