package br.com.flying.dutchman.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface TmdbApiService {

    @GET("movie")
    fun getMovies(): Single<MovieResponse>

    @GET("movie/{movieId}")
    fun getMovie(@Path("movieId") id: Long): Single<MovieResponse>
}