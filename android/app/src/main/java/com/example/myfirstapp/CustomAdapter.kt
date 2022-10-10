package com.example.myfirstapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.database.Movie

class CustomAdapter(private val dataSet: List<Movie>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieID : TextView = view.findViewById(R.id.movieID)
        val movieTitle : TextView = view.findViewById(R.id.movieTitle)
        val movieOverview : TextView = view.findViewById(R.id.movieOverview)
        val movieRelease : TextView = view.findViewById(R.id.movieRelease)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.text_row_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.movieID.text =dataSet[position].uid.toString()
        holder.movieTitle.text = dataSet[position].title
        holder.movieOverview.text =dataSet[position].overview
        holder.movieRelease.text =dataSet[position].release_date
    }

}