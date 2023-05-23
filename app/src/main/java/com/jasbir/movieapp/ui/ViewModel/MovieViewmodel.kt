package com.jasbir.movieapp.ui.ViewModel

import androidx.lifecycle.ViewModel
import com.jasbir.movieapp.data.repository.NowPlayingMovieRepo

class MovieViewmodel : ViewModel() {


    val response = NowPlayingMovieRepo().getMovieData()
    val trending = NowPlayingMovieRepo().getTrend()

}


