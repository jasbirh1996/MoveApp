package com.jasbir.movie.ui.View.UserInterface

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jasbir.movie.R
import com.jasbir.movie.ui.View.Adapter.NowPlayingMovieAdapter
import com.jasbir.movie.ui.View.Adapter.UpcommingMovieAdapter
import com.jasbir.movie.ui.ViewModel.MovieViewmodel
import com.jasbir.movie.util.isNetworkConnected
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkInternet(this)



}

    fun checkInternet(context : Context){

        if(isNetworkConnected.isNetworkConnected(context)){
            intializing()
        }else{

            startActivity(Intent(this,NoInterNet::class.java))
            finish()

        }

    }
    fun intializing(){
        val viewModel = ViewModelProvider(this).get(MovieViewmodel::class.java)

        viewModel.response.observe(this, Observer {


            rv_nowPlaying.apply {

                layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                setHasFixedSize(true)
                adapter = NowPlayingMovieAdapter(it)

            }
            
        })

        viewModel.trending.observe(this, Observer {
            rv_trending.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                setHasFixedSize(true)
                adapter = UpcommingMovieAdapter(it)

            }
        })
        viewall.setOnClickListener {
            startActivity(Intent(this,ViewAll::class.java))
        }
    }



}