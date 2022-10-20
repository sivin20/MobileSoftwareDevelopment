package com.example.myfirstapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.myfirstapp.R

/**
 * A simple [Fragment] subclass.
 * Use the [MovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MovieFragment : Fragment() {

    var title: String = ""
    var overview: String = ""
    var date: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = this.arguments?.getString("title").toString()
        overview = this.arguments?.getString("overview").toString()
        date = this.arguments?.getString("date").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onStart() {
        super.onStart()
        var view = view
        if(view!=null) {
            val titleView : TextView = view.findViewById<TextView>(R.id.detailTitle).apply { text = title }
            val dateView : TextView = view.findViewById<TextView>(R.id.detailDate).apply { text = date }
            val overviewView : TextView = view.findViewById<TextView>(R.id.detailOverview).apply { text = overview }
            val backBtn: Button = view.findViewById<Button>(R.id.backBtn)
            backBtn.setOnClickListener(object :View.OnClickListener{
                override fun onClick(v: View?) {
                    val activity=v!!.context as AppCompatActivity
                    val transaction :FragmentTransaction = activity.supportFragmentManager.beginTransaction()
                    var fragment = activity.supportFragmentManager.findFragmentByTag("movieDetailsTag")
                    if (fragment != null) {
                        transaction.remove(fragment).commit()
                    }

                }
            })
        }
    }
}