package com.saulgargar.gnomedata.data.datasource.local

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.saulgargar.gnomedata.data.datasource.local.model.GnomeUserDB
import com.saulgargar.gnomedata.domain.model.GnomeUser

interface GnomeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGnomes(gnomes: List<GnomeUserDB>)

    @Query("SELECT * FROM GnomeUserDB")
    fun getAllGnomes(): List<GnomeUserDB>

    @Query("SELECT * FROM GnomeUserDB WHERE name = :name")
    fun findByName(name: String): List<GnomeUserDB>
}