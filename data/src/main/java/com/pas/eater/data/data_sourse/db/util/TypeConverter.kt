package com.pas.eater.data.data_sourse.db.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConverter {

    @TypeConverter
    fun fromListString(list: List<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toListString(list: String): List<String> {
        val sType = object: TypeToken<List<String>>() {}.type
        return Gson().fromJson(list, sType)
    }
}
