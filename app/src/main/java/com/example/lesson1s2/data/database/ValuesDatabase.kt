package com.example.lesson1s2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Values::class, ValuesLiked::class], version = 3)
@TypeConverters(Converters::class)
abstract class ValuesDB: RoomDatabase() {
    abstract fun ValuesDao(): ValuesDao
    abstract fun ValuesLikedDao(): ValuesLikedDao

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
                    .fallbackToDestructiveMigration()
                    .build()
                db = instance
                return instance
            }
        }
    }
}