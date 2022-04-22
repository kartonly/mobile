package com.example.lesson1s2.data.database

import androidx.annotation.DrawableRes
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ValuesLiked (
    @PrimaryKey @NonNull
    val name: String,
    @DrawableRes var icon: Int
    )
{
}