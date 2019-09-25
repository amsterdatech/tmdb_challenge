package br.com.flying.dutchman.data.remote

import br.com.flying.dutchman.data.MovieEntity
import br.com.flying.dutchman.data.MoviesDataRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiService: TmdbApiService,
    private val mapper: MovieRemoteEntityMapper
) :
    MoviesDataRepository {
    override fun getMovies(): Observable<List<MovieEntity>> {
        return apiService.getMovies()
            .toObservable().map {
                mapper.mapFromRemote(it.results)
            }
    }

    override fun getMovie(movieId: Long): Single<MovieEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFavourites(): Single<List<MovieEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}