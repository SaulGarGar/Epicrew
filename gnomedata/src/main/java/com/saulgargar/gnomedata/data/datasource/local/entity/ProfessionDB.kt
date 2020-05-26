package com.saulgargar.gnomedata.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ProfessionDB (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val profession: String
)
