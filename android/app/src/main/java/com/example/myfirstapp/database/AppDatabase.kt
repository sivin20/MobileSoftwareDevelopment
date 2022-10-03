package com.example.myfirstapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object{
        @Volatile
        private var instance:AppDatabase? = null

        fun getInstance(context: Context):AppDatabase?{
            if (instance == null){
                synchronized(AppDatabase::class.java){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "movies"
                    )
                        .addCallback(DatabasePrefilling(context))
                        .build()
                }
            }
            return instance
        }
    }
}