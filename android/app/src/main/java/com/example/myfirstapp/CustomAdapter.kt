package com.example.myfirstapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapp.database.AppDatabase
import com.example.myfirstapp.database.Movie
import com.example.myfirstapp.fragment.MovieFragment


class CustomAdapter(private val dataSet: MutableList<Movie>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieTitle : TextView = view.findViewById(R.id.movieTitle)
        val movieOverview : TextView = view.findViewById(R.id.movieOverview)
        val movieRelease : TextView = view.findViewById(R.id.movieRelease)
        val deleteRow : ImageView = view.findViewById(R.id.deleteRow)
    }

    private lateinit var context :Context
    lateinit var db : AppDatabase
    lateinit var deleteThread: DeleteThread

    inner class DeleteThread(title: String) : Thread() {
        var title = title
        override fun run() {
            super.run()
            db.movieDao().deleteFromTitle(title)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.text_row_item, viewGroup, false)
        context = viewGroup.context
        db = AppDatabase.getInstance(context)!!
        return ViewHolder(view)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.movieTitle.text = dataSet[position].title
        var overview = dataSet[position].overview
        holder.movieOverview.text = overview.take(100) + " ..."
        holder.movieRelease.text = dataSet[position].release_date
        holder.deleteRow.setOnClickListener{
            deleteThread = DeleteThread(holder.movieTitle.text.toString())
            deleteThread.start()

            dataSet.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, dataSet.size)
        }
        holder.itemView.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                val activity=v!!.context as AppCompatActivity
                var bundle = createBundle(holder.movieTitle.text.toString(), overview, holder.movieRelease.text.toString())
                val movieFragment = MovieFragment()
                movieFragment.arguments = bundle
                activity.supportFragmentManager.beginTransaction().replace(R.id.constraintLayout, movieFragment, "movieDetailsTag").addToBackStack(null).commit()
                Log.d("tag", movieFragment.tag.toString())
            }
        })
    }

    fun createBundle(title: String, overview: String, date: String) :Bundle {
        var bundle : Bundle = Bundle()
        bundle.putString("title", title)
        bundle.putString("overview", overview)
        bundle.putString("date", date)
        return bundle
    }
}