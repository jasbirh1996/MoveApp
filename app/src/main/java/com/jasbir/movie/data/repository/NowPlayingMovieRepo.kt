package com.jasbir.movie.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.creativaties.modelfactory.Networking.Networking
import com.jasbir.movie.data.dataclass.NowPlayingResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NowPlayingMovieRepo  {
    private  lateinit var disposable : CompositeDisposable

    fun getMovieData() : LiveData<List<NowPlayingResponse.Result>>{

        disposable = CompositeDisposable()

        val nowplayingmovieResppnse = MutableLiveData<List<NowPlayingResponse.Result>>()

        disposable.add(Networking.create()
            .queryGetNowMovie("cc2da08f2f188efc3d713d820c137298","en-US",1).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                {

                    nowplayingmovieResppnse.value = it.results

                },{

                }
            )
            )
        return nowplayingmovieResppnse
    }

    fun getTrend() : LiveData<List<NowPlayingResponse.Result>>{

        disposable = CompositeDisposable()

        val trendinglivedata = MutableLiveData<List<NowPlayingResponse.Result>>()

        disposable.add(Networking.create()
            .queryTrend("cc2da08f2f188efc3d713d820c137298","en-US",1).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe(
                {
                    trendinglivedata.value = it.results

                },{
                    it
                }
            )
        )
        return trendinglivedata
    }











}