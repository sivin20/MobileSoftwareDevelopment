package com.example.myfirstapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase

@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object{
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase?{
            if (instance == null){
                instance = databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "MovieDatabase"
                )
                    .createFromAsset("database/movie.db")
                    .build()
            }
            return instance
        }
    }
}