package com.example.lesson4.database.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.lesson1s2.data.database.Converters
import com.example.lesson1s2.data.database.Values
import com.example.lesson1s2.data.database.ValuesDao

@Database(entities = [Values::class], version = 1)
@TypeConverters(Converters::class)
abstract class ValuesDB: RoomDatabase() {
    abstract fun ValuesDao(): ValuesDao

    companion object {
        @Volatile
        private var db: ValuesDB? = null

        fun getDatabase(context: Context): ValuesDB {
            val tempInstance = db
            if(tempInstance != null){ return tempInstance }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ValuesDB::class.java, "values_database")
                    .build()
                db = instance
                return instance
            }
        }
    }
}