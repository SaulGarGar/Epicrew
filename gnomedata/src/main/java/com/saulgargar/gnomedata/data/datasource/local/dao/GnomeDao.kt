package com.saulgargar.gnomedata.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.saulgargar.gnomedata.data.datasource.local.entity.GnomeUserDB

@Dao
interface GnomeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGnomes(gnomes: List<GnomeUserDB>)

    @Query("SELECT * FROM GnomeUserDB")
    fun recoverGnomes(): List<GnomeUserDB>

    @Query("SELECT * FROM GnomeUserDB WHERE name = :name")
    fun findGnomeByName(name: String): List<GnomeUserDB>

    @Query("SELECT * FROM GnomeUserDB WHERE id = :id")
    fun recoverGnomeById(id: Int): GnomeUserDB
}