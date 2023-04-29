package com.example.newsapp.data.local

import androidx.room.TypeConverter
import com.example.newsapp.data.model.Source
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object TypeConverter {

    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun toSource(value: String?): Source? {
        return gson.fromJson(
            value,
            object : TypeToken<Source>() {
            }.type
        )
    }

    @TypeConverter
    @JvmStatic
    fun fromSource(value: Source?): String? {
        return gson.toJson(value)
    }
}