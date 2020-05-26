package com.saulgargar.gnomedata.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.saulgargar.gnomedata.data.datasource.local.model.GnomeUserDB

@Database(entities = [GnomeUserDB::class], version = 1)
abstract class GnomeDataBase: RoomDatabase() {
    companion object {
        fun build(context: Context) = Room.databaseBuilder(context,GnomeDataBase::class.java,"gnome-db").build()
    }

    abstract fun gnomeDao(): GnomeDao
}