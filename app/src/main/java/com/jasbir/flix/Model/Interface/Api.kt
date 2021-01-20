package com.jasbir.flix.Model.Interface

import com.jasbir.flix.Model.dataclass.CastResponse
import com.jasbir.flix.Model.dataclass.MoviedetailResponse
import com.jasbir.flix.Model.dataclass.NowPlayingResponse
import com.jasbir.flix.Model.dataclass.TrailerResponse
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {


    @GET(EndPoints.Get_NowMovie)
    fun queryGetNowMovie(
        @Query("api_key") api: String,
        @Query("language") lang: String,
        @Query("page") page: Long


    ): Single<NowPlayingResponse>

    @GET(EndPoints.Get_Trend)
    fun queryTrend(
        @Query("api_key") api: String,
        @Query("language") lang: String,
        @Query("page") page: Long


    ): Single<NowPlayingResponse>

    @GET("{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") id: String,
        @Query("api_key") api: String,
        @Query("language") lang: String
    )
            : Single<MoviedetailResponse>

    @GET("{movie_id}/credits")
    fun getCast(
        @Path("movie_id") id: String,
        @Query("api_key") api: String,
        @Query("language") lang: String
    )
            : Single<CastResponse>

    @GET("{movie_id}/videos")
    fun getTrailer(
        @Path("movie_id") id: String,
        @Query("api_key") api: String,
        @Query("language") lang: String
    )
            : Single<TrailerResponse>



}