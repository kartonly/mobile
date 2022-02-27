package com.example.lesson1s2.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lesson1s2.DependencyInjection
import com.example.lesson1s2.data.CurrencyRepository
import com.example.lesson1s2.data.CurrencyResponse
import com.example.lesson1s2.data.models.Currencies
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel() : ViewModel() {
    val data = MutableLiveData<CurrencyResponse>()

    fun main() = runBlocking { // this: CoroutineScope
        launch { // launch a new coroutine and continue
            val cur = DependencyInjection.repository.getAll()
            data.postValue(cur)
            Log.d("MY_TAG", "$data")
        }
    }
}