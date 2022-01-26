package com.example.lesson4.database

import androidx.room.*
import com.example.lesson4.database.Node
import kotlinx.coroutines.flow.Flow

@Dao
interface NodeDao {

    @Query("SELECT * FROM Node")
    fun getAllNodes(): Flow<MutableList<Node>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNode(node: Node)
    @Query("UPDATE Node SET nodes=:nodes WHERE value=:value")
    suspend fun updateNode(value: Int, nodes: MutableList<Node>)
}