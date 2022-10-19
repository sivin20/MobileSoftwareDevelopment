package com.example.myfirstapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.database.AppDatabase
import com.example.myfirstapp.database.Movie
import com.example.myfirstapp.fragment.MovieFragment
import java.io.Console


class CustomAdapter(private val dataSet: List<Movie>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieTitle : TextView = view.findViewById(R.id.movieTitle)
        val movieOverview : TextView = view.findViewById(R.id.movieOverview)
        val movieRelease : TextView = view.findViewById(R.id.movieRelease)
        val deleteRow : ImageView = view.findViewById(R.id.deleteRow)
    }

    private lateinit var context :Context
    lateinit var db : AppDatabase

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.text_row_item, viewGroup, false)
        context = viewGroup.context
        db = AppDatabase.getInstance(context)!!
        return ViewHolder(view)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.movieTitle.text = dataSet[position].title
        holder.movieOverview.text =dataSet[position].overview
        holder.movieRelease.text =dataSet[position].release_date
        holder.deleteRow.setOnClickListener{
            db.movieDao().deleteFromTitle(holder.movieTitle.text.toString())
            holder.movieTitle.text = "This has been deleted"
            Log.d("Movie Deletion", "This Works" + holder.movieTitle.text.toString())
        }
        holder.itemView.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                val activity=v!!.context as AppCompatActivity
                val movieFragment=MovieFragment()
                activity.supportFragmentManager.beginTransaction().replace(R.id.constraintLayout, movieFragment).addToBackStack(null).commit()
            }
        })
    }
}