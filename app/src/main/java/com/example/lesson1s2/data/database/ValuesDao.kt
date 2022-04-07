package com.example.lesson1s2.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.lesson1s2.data.database.Values
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.util.*
import android.icu.lang.UCharacter.GraphemeClusterBreak.T

@Dao
interface ValuesDao {

    @Query("SELECT * FROM `Values`")
    fun getAllValues():  Flow<MutableList<Values>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNode(values: Values)
}