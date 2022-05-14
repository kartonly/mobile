package com.example.lesson1s2.data.database

import android.text.format.DateFormat
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.*

@Entity
data class Values(
    @PrimaryKey(autoGenerate = true) @NonNull val id: Int,
    var value: String,
    val cost: Double,
    val finalCost: Double,
    val date: Long
){
    ///
}