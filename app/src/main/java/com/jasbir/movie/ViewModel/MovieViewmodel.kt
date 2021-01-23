package com.jasbir.movie.ViewModel

import androidx.lifecycle.ViewModel
import com.jasbir.movie.Model.repository.NowPlayingMovieRepo

class MovieViewmodel : ViewModel() {


    val response = NowPlayingMovieRepo().getMovieData()
    val trending = NowPlayingMovieRepo().getTrend()

}


