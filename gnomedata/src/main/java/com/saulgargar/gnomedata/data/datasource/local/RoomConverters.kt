package com.saulgargar.gnomedata.data.datasource.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RoomConverters {
    @TypeConverter
    fun fromString(value:String): List<String>{
        val listType = object: TypeToken<List<String>>() {}.type
        return Gson().fromJson(value,listType)
    }

    @TypeConverter
    fun toString(list: List<String>): String{
        val gson = Gson()
        return gson.toJson(list)
    }
}