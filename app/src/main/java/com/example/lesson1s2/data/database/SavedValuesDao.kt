package com.example.lesson1s2.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lesson1s2.data.database.Values
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.util.*
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.room.TypeConverters


@Dao
interface SavedValuesDao {

    @Query("SELECT * FROM `SavedValues` ORDER BY `like`=0")
    fun getAllSavedValues():  Flow<MutableList<SavedValues>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSavedValue(savedValues: SavedValues)
    @Update
    suspend fun updateSavedValues(savedValues: SavedValues)

    @Query("UPDATE `SavedValues` SET 'cost'=:cost WHERE 'value'=:value")
    fun updateCost(value: String, cost: Double)

}