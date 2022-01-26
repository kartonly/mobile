package com.example.lesson4.database

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Node(
    @PrimaryKey @NonNull
    val value: Int,
    val nodes: MutableList<Node>
){
    ///
}