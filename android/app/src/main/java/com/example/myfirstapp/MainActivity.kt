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
import com.example.myfirstapp.database.Movie

class MainActivity : AppCompatActivity() {

    lateinit var db : AppDatabase
    lateinit var adapter : CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = AppDatabase.getInstance(this)!!
        setRecyclerView()

    }

    private fun setRecyclerView() {
        var recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        var layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL,false)
        recyclerView.layoutManager = layoutManager

        adapter = CustomAdapter(db.movieDao().getAll())
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    fun sendMessage(view: View) {
        val editText = findViewById<EditText>(R.id.addTitle)
        val message = editText.text.toString();
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }
    fun addMovie(view: View) {
        val title = findViewById<EditText>(R.id.addTitle)
        val date = findViewById<EditText>(R.id.addDate)
        val overview = findViewById<EditText>(R.id.addOverview)
        val uid = (0..10000000).random()
        val movie =  Movie(uid, title.text.toString(), overview.text.toString(), date.text.toString())
        db.movieDao().insert(movie)
        title.getText().clear()
        overview.getText().clear()
        date.getText().clear()
        setRecyclerView()
    }
}