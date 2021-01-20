package com.jasbir.flix.Model.repository

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.creativaties.modelfactory.Networking.Networking
import com.jasbir.flix.Model.dataclass.CastResponse
import com.jasbir.flix.Model.dataclass.MoviedetailResponse
import com.jasbir.flix.Model.dataclass.TrailerResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailRepo{
    private  lateinit var disposable : CompositeDisposable
    fun getMovieDetail( id: String): MutableLiveData<MoviedetailResponse> {
        disposable = CompositeDisposable()

        val getDetailLivedata = MutableLiveData<MoviedetailResponse>()

        disposable.add(
            Networking.create()
            .getMovieDetails(id,"cc2da08f2f188efc3d713d820c137298","en-US").subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                {
                    getDetailLivedata.value = it

                },{
                        it.localizedMessage
                }
            )
        )
        return getDetailLivedata



    }
    fun getCast( id: String): MutableLiveData<CastResponse> {
        disposable = CompositeDisposable()

        val getDetailLivedata = MutableLiveData<CastResponse>()

        disposable.add(
            Networking.create()
                .getCast(id,"cc2da08f2f188efc3d713d820c137298","en-US").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                    {
                        getDetailLivedata.value = it

                    },{
                        it.localizedMessage
                    }
                )
        )
        return getDetailLivedata



    }
    fun getReview( id: String): MutableLiveData<TrailerResponse> {
        disposable = CompositeDisposable()

        val getDetailLivedata = MutableLiveData<TrailerResponse>()

        disposable.add(
            Networking.create()
                .getTrailer(id,"cc2da08f2f188efc3d713d820c137298","en-US").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                    {
                        getDetailLivedata.value = it

                    },{
                        it.localizedMessage
                    }
                )
        )
        return getDetailLivedata



    }



}