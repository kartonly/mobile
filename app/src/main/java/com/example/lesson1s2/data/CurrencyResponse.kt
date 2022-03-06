package com.example.lesson1s2.data

import java.util.*

data class CurrencyResponse (
    val success: Boolean,
    val timestamp: Long,
    val base: String,
    val date: String,
    val rates: Map<String, Double>
    )