package com.example.lesson1s2.data

import com.example.lesson1s2.data.sources.RemoteDataSource

class CurrencyRepository {

    suspend fun getRemoteSource(){
        return RemoteDataSource().getSource()
    }
}