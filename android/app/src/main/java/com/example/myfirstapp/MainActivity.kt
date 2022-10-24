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
import com.example.myfirstapp.database.AppDatabase
import com.example.myfirstapp.database.Movie

class MainActivity : AppCompatActivity() {

    lateinit var db : AppDatabase
    lateinit var adapter : CustomAdapter
    var list :MutableList<Movie> = arrayListOf()
    lateinit var listThread : ListThread
    lateinit var recyclerView : RecyclerView

    inner class ListThread : Thread() {
        override fun run() {
            super.run()
            list = db.movieDao().getAll() as MutableList<Movie>
            runOnUiThread(Runnable {
                adapter.notifyDataSetChanged()
                adapter = CustomAdapter(list)
                recyclerView.adapter = adapter
                Log.d("Log", list.size.toString())
            })
        }
    }

    inner class AddToDatabaseThread(title: String, overview: String, date: String) : Thread() {
        val movie =  Movie(0, title, overview, date)
        override fun run() {
            super.run()
            db.movieDao().insert(movie)
            runOnUiThread(Runnable{
                list.add(movie)
                adapter.notifyDataSetChanged()
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = AppDatabase.getInstance(this)!!
        listThread = ListThread()
        adapter = CustomAdapter(list)



        setRecyclerView()
        listThread.start()
    }
    private fun setRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        var layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL,false)
        recyclerView.layoutManager = layoutManager
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
        var thread = AddToDatabaseThread(title.text.toString(), overview.text.toString(), date.text.toString())
        thread.start()
        title.getText().clear()
        overview.getText().clear()
        date.getText().clear()
    }
}