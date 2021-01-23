package com.jasbir.movie.View.UserInterface

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.shimmer.ShimmerFrameLayout
import com.jasbir.movie.R
import com.jasbir.movie.View.Adapter.NowPlayingMovieAdapter
import com.jasbir.movie.View.Adapter.UpcommingMovieAdapter
import com.jasbir.movie.ViewModel.MovieViewmodel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(MovieViewmodel::class.java)

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

}

}