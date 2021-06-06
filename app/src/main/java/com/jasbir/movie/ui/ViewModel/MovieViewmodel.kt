package com.jasbir.movie.ui.ViewModel

import androidx.lifecycle.ViewModel
import com.jasbir.movie.data.repository.NowPlayingMovieRepo

class MovieViewmodel : ViewModel() {


    val response = NowPlayingMovieRepo().getMovieData()
    val trending = NowPlayingMovieRepo().getTrend()

}


