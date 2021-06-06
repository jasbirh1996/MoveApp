package com.jasbir.movie.ui.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jasbir.movie.data.dataclass.CastResponse
import com.jasbir.movie.data.dataclass.MoviedetailResponse
import com.jasbir.movie.data.dataclass.TrailerResponse
import com.jasbir.movie.data.repository.DetailRepo

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