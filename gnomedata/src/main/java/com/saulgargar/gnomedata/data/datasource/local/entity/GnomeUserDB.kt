package com.saulgargar.gnomedata.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class GnomeUserDB (
    @PrimaryKey(autoGenerate = false)
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