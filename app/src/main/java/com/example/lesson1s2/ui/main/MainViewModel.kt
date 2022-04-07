package com.example.lesson1s2.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.lesson1s2.DependencyInjection
import com.example.lesson1s2.data.CurrencyResponse
import com.example.lesson1s2.data.database.Values
import com.example.lesson1s2.data.database.ValuesRepository
import com.example.lesson4.database.databases.ValuesDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.util.*

class MainViewModel(application: Application) : ViewModel() {
    val data = MutableLiveData<CurrencyResponse>()

//    suspend fun <T> Flow<MutableList<T>>.flattenToList() =
//        flatMapConcat { it.asFlow() }.toList()

    private val repository: ValuesRepository
    val getAllNodes: LiveData<MutableList<Values>>

    init {
        val valuesDao = ValuesDB.getDatabase(application).ValuesDao()
        repository = ValuesRepository(valuesDao)
        getAllNodes = repository.getAllValues.asLiveData()
    }


    public fun insertValue(values: Values)
    = viewModelScope.launch(Dispatchers.IO) { repository.insertValue(values) }

    fun main() = runBlocking { // this: CoroutineScope
        launch { // launch a new coroutine and continue
            val cur = DependencyInjection.repository.getAll()
            data.postValue(cur)
            Log.d("MY_TAG", "$data")
        }
    }
}