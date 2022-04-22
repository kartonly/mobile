package com.example.lesson1s2.data.database


import java.time.LocalDate
import java.util.*

class ValuesLikedRepository(private val valuesLikedDao: ValuesLikedDao) {

    var getAllLiked = valuesLikedDao.getAllLiked();

    suspend fun insertLiked(value: ValuesLiked){
        valuesLikedDao.insertLiked(value)
    }

    public fun deleteLiked(value: ValuesLiked){
        valuesLikedDao.deleteLiked(value)
    }
}