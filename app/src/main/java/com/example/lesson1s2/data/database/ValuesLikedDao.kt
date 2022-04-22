package com.example.lesson1s2.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ValuesLikedDao {
    @Query("SELECT * FROM `ValuesLiked`")
    fun getAllLiked(): Flow<MutableList<ValuesLiked>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLiked(value: ValuesLiked)
    @Delete
    public fun deleteLiked(value: ValuesLiked)
}