package com.example.lesson1s2.ui.main

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.example.lesson1s2.DependencyInjection
import com.example.lesson1s2.R
import com.example.lesson1s2.data.CurrencyResponse
import com.example.lesson1s2.data.asyncData.CurrencyHolder
import com.example.lesson1s2.data.asyncData.HistoryAdapter
import com.example.lesson1s2.data.database.*
import com.example.lesson1s2.data.models.Currency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.sql.Time
import java.time.LocalTime
import java.util.*

class MainViewModel(application: Application) : ViewModel() {
    var oldTime:Long = 0
    var prefs: SharedPreferences? = null
    var sharedEditor: SharedPreferences.Editor? = null
    public fun isFirstStart(): Boolean? {
        if (prefs?.getBoolean("firstrun", true) == true) {
            // Do first run stuff here then set 'firstrun' as false
            // using the following line to edit/commit prefs
            prefs!!.edit().putBoolean("firstrun", false).commit();
            return true
        }else {
            return false
        }
    }

    ///data from api
    val data = MutableLiveData<CurrencyResponse>()
    val valuesList: MutableList<Currency> = mutableListOf()

    ///values(history) from db
    private val repository: ValuesRepository
    val getAllValues: LiveData<MutableList<Values>>
    val getByMonth: LiveData<MutableList<Values>>
    val getByWeek: LiveData<MutableList<Values>>
    lateinit var getByValue: LiveData<MutableList<Values>>
    lateinit var getBetween: LiveData<MutableList<Values>>

    ///values(saved) from db
    private val savedRepository: SavedValuesRepository
    val getAllSavedValues: LiveData<MutableList<SavedValues>>

    ///insert values(history) into db
    public fun insertValue(values: Values)
            = viewModelScope.launch(Dispatchers.IO) { repository.insertValue(values) }

    ///get values(history) from db by value name
    public fun getByValue(value: String): LiveData<MutableList<Values>> {
        getByValue = repository.getByValue(value).asLiveData()
        return getByValue
    }

    ///get values(history) from db by value name
    public fun getBetween(filter: String): LiveData<MutableList<Values>> {
        getBetween = repository.getBetween(filter).asLiveData()
        return getBetween
    }

    ///insert values(saved) into db
    public fun insertSavedValue(savedValues: SavedValues)
            = viewModelScope.launch(Dispatchers.IO) { savedRepository.insertSaved(savedValues) }

    ///update values(saved) in db
    public fun updateSavedValue(savedValues: SavedValues)
            = viewModelScope.launch(Dispatchers.IO) { savedRepository.updateSaved(savedValues) }

    ///update cost(saved) in db
    public fun updateCost(value: String, cost: Double)
            = viewModelScope.launch(Dispatchers.IO) { savedRepository.updateCost(value, cost) }

    init {
        val valuesDao = ValuesDB.getDatabase(application).ValuesDao()
        repository = ValuesRepository(valuesDao)
        getAllValues = repository.getAllValues.asLiveData()
        getByMonth = repository.getByMonth.asLiveData()
        getByWeek = repository.getByWeek.asLiveData()

        val savedValuesDao = ValuesDB.getDatabase(application).SavedValuesDao()
        savedRepository = SavedValuesRepository(savedValuesDao)
        getAllSavedValues = savedRepository.getAllSavedValues.asLiveData()
    }

//    ///get values from api
//    suspend fun getCur(): MutableList<Currency>{
//        val rates = CurrencyHolder.getCur().toList()
//        for (i in rates){
//            valuesList.add(Currency(i.first, i.second, R.drawable.fav))
//        }
//        return valuesList
//    }

    suspend fun changeValues(): String {
        if (isFirstStart() == true){
            firstStart()
        }
//        firstStart()

        var time = Calendar.getInstance().timeInMillis
        if ((time - oldTime)>3000){
            Log.d("MY_TAG", time.toString())
            val rates = CurrencyHolder.getCur().toList()
            for (i in rates){
                updateCost(i.first, i.second)
            }
            oldTime = time
        }
        return time.toString()+"  "+oldTime.toString()
    }

    suspend fun firstStart(){
        val rates = CurrencyHolder.getCur().toList()
        for (i in rates){
            var save = SavedValues(i.first, i.second, false)
            insertSavedValue(save)
        }
    }

}

