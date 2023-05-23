package com.jasbir.movieapp.ui.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jasbir.movieapp.data.dataclass.CastResponse
import com.jasbir.movieapp.data.dataclass.MoviedetailResponse
import com.jasbir.movieapp.data.dataclass.TrailerResponse
import com.jasbir.movieapp.data.repository.DetailRepo

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