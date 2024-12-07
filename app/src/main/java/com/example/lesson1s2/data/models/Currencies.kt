package com.example.lesson1s2.data.models

import androidx.annotation.DrawableRes
import java.util.*

class Currencies(
    val timestamp: Long,
    val base: String,
    val rates: List<Currency>
)

data class Currency(
    val name: String,
    val value: Double,
    @DrawableRes var icon: Int
)