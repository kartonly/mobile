package com.example.lesson1s2.data

import retrofit2.http.GET

interface CurrencyApi {

    @GET("api/latest?access_key=0bc25c00705dc48cd60b5252abbe1d04")
    suspend fun getCurrencies(): CurrencyResponse
}