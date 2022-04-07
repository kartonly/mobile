package com.example.lesson1s2.data.asyncData

import android.util.Log
import com.example.lesson1s2.DependencyInjection
import com.example.lesson1s2.data.CurrencyResponse
import com.example.lesson1s2.data.database.Values
import com.example.lesson1s2.data.database.ValuesRepository
import com.example.lesson1s2.data.models.Currencies
import com.example.lesson1s2.data.models.Currency
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

typealias activityListener = () -> Unit

object CurrencyHolder {

    suspend fun getCur(): Map<String,Double> {
        val cur = DependencyInjection.repository.getAll()
        return cur.rates
    }

}
