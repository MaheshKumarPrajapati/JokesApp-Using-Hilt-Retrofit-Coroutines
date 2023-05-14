package com.example.jokesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jokesapp.R
import com.example.jokesapp.other.Utils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var jokesList:ArrayList<String> = ArrayList()
        var adapter = JokesAdapter()

        rvJokess.layoutManager = LinearLayoutManager(this)

        rvJokess.adapter = adapter
        Utils().getJokesFromPrefrence(this).apply {
          if(this.isNotEmpty()) {
              jokesList.addAll(this)
              val reverseList: List<String> = this.reversed();
              adapter.submitList(reverseList)
          }
        }

        mainViewModel.res.observe(this, Observer {
                    it.let {res->
                               if(jokesList.size==10) {
                                     jokesList.removeAt(0)
                                     jokesList.add(it)
                                 }else {
                                     jokesList.add(it)
                                 }
                                Utils().saveJokesToPrefrence(this,jokesList)
                                val reverseList: List<String> = jokesList.reversed();
                                adapter.submitList(reverseList)


           } })
    }
}