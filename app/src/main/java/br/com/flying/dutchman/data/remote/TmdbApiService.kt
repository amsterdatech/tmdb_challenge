package br.com.flying.dutchman.data.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApiService {

    @GET("discover/movie")
    fun getMovies(@Query("page") page: Int = 1): Single<MovieResponse>

    @GET("movie/{movieId}")
    fun getMovie(@Path("movieId") id: Int): Single<Movie>
}