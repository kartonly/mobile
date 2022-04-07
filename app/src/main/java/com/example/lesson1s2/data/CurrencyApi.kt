package com.example.lesson1s2.data

import retrofit2.http.GET

interface CurrencyApi {

    @GET("api/latest?access_key=8cb87f12bf7907a881b1e1f6ca191c29")
    suspend fun getCurrencies(): CurrencyResponse
}