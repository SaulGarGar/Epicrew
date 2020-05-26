package com.saulgargar.gnomedata.data.datasource.local

import com.saulgargar.gnomedata.data.datasource.local.model.GnomeUserDB
import com.saulgargar.gnomedata.domain.model.GnomeUser

fun GnomeUser.toGnomeUserDB() =
    GnomeUserDB(
        id,
        name,
        thumbNail,
        age,
        weight,
        height,
        hairColor,
        professions,
        friends
    )

fun GnomeUserDB.toDomain() =
    GnomeUser(
        id,
        name,
        thumbNail,
        age,
        weight,
        height,
        hairColor,
        professions,
        friends
    )