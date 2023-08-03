package com.example.dogsapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Dogs::class), version = 1, exportSchema = false)
abstract class DogsRoomDatabase : RoomDatabase() {
    abstract fun DogsDao(): DogsDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: DogsRoomDatabase? = null

        fun getDatabase(context: Context): DogsRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DogsRoomDatabase::class.java,
                    "dogs_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}