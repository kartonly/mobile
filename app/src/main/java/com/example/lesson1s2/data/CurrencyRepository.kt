package com.example.lesson1s2.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lesson1s2.data.sources.RemoteDataSource
import kotlinx.coroutines.Job

class CurrencyRepository {

    suspend fun getAll(): MutableLiveData<CurrencyResponse> {
        RemoteDataSource().getSource()
        return RemoteDataSource().result
    }
}