package com.example.lesson1s2.data

import retrofit2.http.GET

interface CurrencyApi {

    @GET("api/latest?access_key=01ec24536552f05d09f3c615631ebfb3")
    suspend fun getCurrencies(): CurrencyResponse
}