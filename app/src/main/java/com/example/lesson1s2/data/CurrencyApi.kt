package com.example.lesson1s2.data

import retrofit2.http.GET

interface CurrencyApi {

    @GET("api/latest?access_key=c7261fce3fda1e3c26a43f861a66c408&format=1")
    suspend fun getCurrencies(): CurrencyResponse
}