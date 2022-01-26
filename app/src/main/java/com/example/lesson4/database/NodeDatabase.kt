package com.example.lesson4.database.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lesson4.database.Converters
import com.example.lesson4.database.Node
import com.example.lesson4.database.NodeDao

@Database(entities = [Node::class], version = 1)
@TypeConverters(Converters::class)
abstract class NodeDB: RoomDatabase() {
    abstract fun NodeDao(): NodeDao

    companion object {
        @Volatile
        private var db: NodeDB? = null

        fun getDatabase(context: Context): NodeDB {
            val tempInstance = db
            if(tempInstance != null){ return tempInstance }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NodeDB::class.java, "node_database")
                    .build()
                db = instance
                return instance
            }
        }
    }
}