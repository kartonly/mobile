package com.example.lesson1s2.data.database

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.*

@Entity
data class SavedValues(
    @PrimaryKey @NonNull
    var value: String,
    val cost: Double,
    var like: Boolean,
){
    ///
}