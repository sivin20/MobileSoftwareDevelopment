package com.example.myfirstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myfirstapp.database.AppDatabase

class MainActivity : AppCompatActivity() {

    lateinit var db : AppDatabase
    lateinit var adapter : CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = AppDatabase.getInstance(this)!!

        var recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        var layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL,false)
        recyclerView.layoutManager = layoutManager
        if(db.movieDao().getAll().isNotEmpty()) {
            Log.d("isNotEmpty", "It aint empty")
            for(movie in db.movieDao().getAll()) {
                Log.d("title", movie.title.toString())
            }
        }

        adapter = CustomAdapter(db.movieDao().getAll())
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    fun sendMessage(view: View) {
        val editText = findViewById<EditText>(R.id.editTextTextPersonName)
        val message = editText.text.toString();
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }
}