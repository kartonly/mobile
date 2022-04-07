package com.example.lesson1s2.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.time.LocalDate
import java.util.*

class Converters {
    @TypeConverter
    fun toJsonValue(value: String): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toList(value: String) = Gson().fromJson(value, Array<Values>::class.java).toMutableList()
}