package com.example.lesson1s2.data.database


import java.time.LocalDate
import java.util.*

class SavedValuesRepository(private val savedValuesDao: SavedValuesDao) {

    var getAllSavedValues = savedValuesDao.getAllSavedValues();

    suspend fun insertSaved(savedValues: SavedValues){
        savedValuesDao.insertSavedValue(savedValues)
    }

    suspend fun updateSaved(savedValues: SavedValues){
        savedValuesDao.updateSavedValues(savedValues)
    }

    suspend fun updateCost(value: String, cost: Double){
        savedValuesDao.updateCost(value, cost)
    }
}