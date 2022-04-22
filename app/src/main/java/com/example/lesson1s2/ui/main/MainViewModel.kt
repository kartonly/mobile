package com.example.lesson1s2.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.lesson1s2.DependencyInjection
import com.example.lesson1s2.R
import com.example.lesson1s2.data.CurrencyResponse
import com.example.lesson1s2.data.asyncData.CurrencyHolder
import com.example.lesson1s2.data.database.Values
import com.example.lesson1s2.data.database.ValuesLiked
import com.example.lesson1s2.data.database.ValuesLikedRepository
import com.example.lesson1s2.data.database.ValuesRepository
import com.example.lesson1s2.data.models.Currency
import com.example.lesson1s2.data.database.ValuesDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(application: Application) : ViewModel() {
    ///data from api
    val data = MutableLiveData<CurrencyResponse>()
    val valuesList: MutableList<Currency> = mutableListOf()

    ///values(history) from db
    private val repository: ValuesRepository
    val getAllValues: LiveData<MutableList<Values>>

    ///values(liked) from db
    private val repositoryLiked: ValuesLikedRepository
    val getAllLiked: LiveData<MutableList<ValuesLiked>>

    ///insert values(history) into db
    public fun insertValue(values: Values)
            = viewModelScope.launch(Dispatchers.IO) { repository.insertValue(values) }

    ///insert values(liked) into db
    public fun insertLiked(value: ValuesLiked)
            = viewModelScope.launch(Dispatchers.IO) { repositoryLiked.insertLiked(value) }

    ///delete values(liked) from db
    public fun deleteLiked(value: ValuesLiked)
            = viewModelScope.launch(Dispatchers.IO) { repositoryLiked.deleteLiked(value) }

    init {
        val valuesDao = ValuesDB.getDatabase(application).ValuesDao()
        repository = ValuesRepository(valuesDao)
        getAllValues = repository.getAllValues.asLiveData()

        val valuesLikedDao = ValuesDB.getDatabase(application).ValuesLikedDao()
        repositoryLiked = ValuesLikedRepository(valuesLikedDao)
        getAllLiked = repositoryLiked.getAllLiked.asLiveData()
    }


    ///log values from api
    fun main() = runBlocking { // this: CoroutineScope
        launch { // launch a new coroutine and continue
            val cur = DependencyInjection.repository.getAll()
            data.postValue(cur)
            Log.d("MY_TAG", "$data")
        }
    }

    ///get values from api
    suspend fun getCur(): MutableList<Currency>{
        val rates = CurrencyHolder.getCur().toList()
        for (i in rates){
            valuesList.add(Currency(i.first, i.second, R.drawable.fav))
        }
        return valuesList
    }
}