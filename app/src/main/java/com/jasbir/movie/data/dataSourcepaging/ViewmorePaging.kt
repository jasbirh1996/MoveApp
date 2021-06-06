package com.jasbir.movie.data.dataSourcepaging

import android.app.Activity
import androidx.paging.rxjava2.RxPagingSource
import com.jasbir.movie.data.Interface.Api
import com.jasbir.movie.data.dataclass.NowPlayingResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class ViewmorePaging(var networkService: Api, var context: Activity) : RxPagingSource<Int, NowPlayingResponse.Result>() {
    //    val progress = Dialog(context, android.R.style.Theme_Panel)
    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, NowPlayingResponse.Result>> {
        var currentPageKey = params.key ?: 1

//        progress.setContentView(R.layout.dialog_progress)
//        progress.setCanceledOnTouchOutside(false)
//        progress.show()
        return networkService.queryTrend(
        "cc2da08f2f188efc3d713d820c137298",
            "en-US"
        ,2
        )
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .map { toLoadResult(it, currentPageKey) }


    }

    private fun toLoadResult(response: NowPlayingResponse, position: Int): LoadResult<Int, NowPlayingResponse.Result> {
        return LoadResult.Page(
            data = response.results,
            prevKey = if (position == 1) null else position - 1,
            nextKey = if (position == response.page) null else position + 1
        )
    }


}