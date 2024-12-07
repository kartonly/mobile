package com.example.lesson1s2.data.database


import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class ValuesRepository(private val valuesDao: ValuesDao) {

    var getAllValues = valuesDao.getAllValues();
    suspend fun insertValue(values: Values){
        valuesDao.insertValue(values)
    }
    var getByMonth = valuesDao.getValuesByMonth(getMonth())
    var getByWeek = valuesDao.getValuesByMonth(getWeek())

    fun getByValue(value: String): Flow<MutableList<Values>> {
        return valuesDao.getValuesByValues(value)
    }

    fun getBetween(filter: String): Flow<MutableList<Values>> {
        return valuesDao.getValuesBetween(getValueFirst(filter), getValueSecond(filter))
    }

    fun getMonth(): Long {
        var date = LocalDate.now().minusDays(31);
        var final = SimpleDateFormat("yyyy-MM-dd").parse(date.toString())
        return final.time
    }
    fun getWeek(): Long {
        var date = LocalDate.now().minusDays(7);
        var final = SimpleDateFormat("yyyy-MM-dd").parse(date.toString())
        return final.time
    }

    fun getValueFirst(filter: String): Long {
        var str = filter.replace("d", "")
        val strs = str.split(",").toTypedArray()

        return strs[0].toLong();
    }

    fun getValueSecond(filter: String): Long {
        var str = filter.replace("d", "")
        val strs = str.split(",").toTypedArray()

        return strs[1].toLong();
    }

}