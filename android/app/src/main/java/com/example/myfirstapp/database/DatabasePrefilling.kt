package com.example.myfirstapp.database

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myfirstapp.R
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader

class DatabasePrefilling(private val context: Context) :RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
    }

    private fun loadJSONArray(context: Context): JSONArray?{

        val inputStream = context.resources.openRawResource(R.raw.movies)

        BufferedReader(inputStream.reader()).use {
            return JSONArray(it.readText())
        }
    }

    private suspend fun fillWithStartingNotes(context: Context){

        val dao = AppDatabase.getInstance(context)?.movieDao()

        try {
            val notes = loadJSONArray(context)
            if (notes != null){
                for (i in 0 until notes.length()){
                    val item = notes.getJSONObject(i)
                    val uid = item.getInt("uid");
                    val title = item.getString("title")
                    val overview = item.getString("overview")
                    val release_date = item.getString("release_date")

                    val movie = Movie(
                        uid, title, overview, release_date
                    )

                    dao?.insert(movie)
                }
            }
        }

        catch (e: JSONException) {

        }
    }
}