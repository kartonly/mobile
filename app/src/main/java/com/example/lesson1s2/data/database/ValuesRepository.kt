package com.example.lesson1s2.data.database


import java.time.LocalDate
import java.util.*

class ValuesRepository(private val valuesDao: ValuesDao) {

    var getAllValues = valuesDao.getAllValues();
    suspend fun insertValue(values: Values){
        valuesDao.insertValue(values)
    }
}