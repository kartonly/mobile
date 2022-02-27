package com.example.lesson1s2.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lesson1s2.data.sources.RemoteDataSource
import kotlinx.coroutines.Job

class CurrencyRepository(private val remoteData: RemoteDataSource = RemoteDataSource()) {

    suspend fun getAll(): CurrencyResponse{
        return remoteData.getSource()
    }
}