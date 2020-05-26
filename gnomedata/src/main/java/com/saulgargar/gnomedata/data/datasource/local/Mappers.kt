package com.saulgargar.gnomedata.data.datasource.local

import com.saulgargar.gnomedata.data.datasource.local.entity.GnomeUserDB
import com.saulgargar.gnomedata.data.datasource.local.entity.HairColorDB
import com.saulgargar.gnomedata.data.datasource.local.entity.ProfessionDB
import com.saulgargar.gnomedata.domain.model.GnomeUser
import com.saulgargar.gnomedata.domain.model.HairColor
import com.saulgargar.gnomedata.domain.model.Profession

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

fun HairColor.toHairColorDB() =
    HairColorDB(
        id,
        color
    )

fun HairColorDB.toDomain() =
    HairColor(
        id,
        color
    )

fun Profession.toProfessionDB() =
    ProfessionDB(
        id,
        profession
    )

fun ProfessionDB.toDomain() =
    Profession(
        id,
        profession
    )