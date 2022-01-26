package com.example.lesson4.database

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun toJson(value: MutableList<Node>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toList(value: String) = Gson().fromJson(value, Array<Node>::class.java).toMutableList()
}