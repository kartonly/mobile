package com.example.lesson1s2.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.time.LocalDate
import java.util.*

class Converters {

    @TypeConverter
    fun toList(savedValues: SavedValues) = Gson().fromJson(savedValues.value, Array<SavedValues>::class.java).toList()

}