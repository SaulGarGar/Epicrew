package com.saulgargar.gnomedata.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.saulgargar.gnomedata.data.datasource.local.entity.ProfessionDB

@Dao

interface ProfessionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProfessions(professions: List<ProfessionDB>)

    @Query("SELECT * FROM ProfessionDb")
    fun recoverProfessions(): List<ProfessionDB>
}