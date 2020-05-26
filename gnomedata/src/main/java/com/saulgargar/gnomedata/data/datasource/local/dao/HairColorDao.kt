package com.saulgargar.gnomedata.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.saulgargar.gnomedata.data.datasource.local.entity.HairColorDB

@Dao
interface HairColorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHairColors(colors: List<HairColorDB>)

    @Query("SELECT * FROM HairColorDB")
    fun recoverHairColors(): List<HairColorDB>
}