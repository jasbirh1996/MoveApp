package com.jasbir.flix.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.creativaties.modelfactory.Networking.Networking
import com.jasbir.flix.Model.repository.NowPlayingMovieRepo

class MovieViewmodel : ViewModel() {


    val response = NowPlayingMovieRepo().getMovieData()
    val trending = NowPlayingMovieRepo().getTrend()

}


