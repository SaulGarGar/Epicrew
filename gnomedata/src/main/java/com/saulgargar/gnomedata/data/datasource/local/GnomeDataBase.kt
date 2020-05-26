package com.saulgargar.gnomedata.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.saulgargar.gnomedata.data.datasource.local.dao.GnomeDao
import com.saulgargar.gnomedata.data.datasource.local.dao.HairColorDao
import com.saulgargar.gnomedata.data.datasource.local.dao.ProfessionDao
import com.saulgargar.gnomedata.data.datasource.local.entity.GnomeUserDB
import com.saulgargar.gnomedata.data.datasource.local.entity.HairColorDB
import com.saulgargar.gnomedata.data.datasource.local.entity.ProfessionDB

@Database(entities = [GnomeUserDB::class, HairColorDB::class, ProfessionDB::class], version = 1)
@TypeConverters(RoomConverters::class)
abstract class GnomeDataBase: RoomDatabase() {

    abstract fun getGnomeDao(): GnomeDao
    abstract fun getHairColorDao(): HairColorDao
    abstract fun getProfessionDao(): ProfessionDao

    companion object {
        fun build(context: Context) = Room.databaseBuilder(context,GnomeDataBase::class.java,"gnome-db").build()
    }
}