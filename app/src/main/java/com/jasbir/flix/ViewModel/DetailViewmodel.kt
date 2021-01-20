package com.jasbir.flix.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jasbir.flix.Model.dataclass.CastResponse
import com.jasbir.flix.Model.dataclass.MoviedetailResponse
import com.jasbir.flix.Model.dataclass.TrailerResponse
import com.jasbir.flix.Model.repository.DetailRepo
import com.jasbir.flix.Model.repository.NowPlayingMovieRepo

class DetailViewmodel : ViewModel() {

    var movieid: String? = null
    var responseDetail = MutableLiveData<MoviedetailResponse>()
    var responseCast = MutableLiveData<CastResponse>()
    var responseReview = MutableLiveData<TrailerResponse>()


    fun invokeApi() {
        responseDetail = DetailRepo().getMovieDetail(movieid!!)
        responseCast = DetailRepo().getCast(movieid!!)
        responseReview = DetailRepo().getReview(movieid!!)

    }

    fun setId(id: String) {
        movieid = id
    }





}