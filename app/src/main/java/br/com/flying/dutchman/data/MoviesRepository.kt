package br.com.flying.dutchman.data

import android.util.Log
import br.com.flying.dutchman.data.cache.RoomRepository
import br.com.flying.dutchman.data.remote.ApiRepository
import br.com.flying.dutchman.domain.MoviesMapper
import com.dutchtechnologies.domain.Movie
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MoviesRepository @Inject constructor(
    private val api: ApiRepository,
    private val db: RoomRepository,
    private val mapper: MoviesMapper
) {

    var memoryCache = listOf<MovieEntity>()


    fun getMovies(): Observable<List<Movie>> {
        val networkWithSave = api.getMovies().doOnNext {
            Completable.fromCallable {
                db.save(it)
            }.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe {
                    Log.d("ROOM", "SAVING MOVIES")
                }

        }
        return networkWithSave.map { list ->
            list.map { movie ->
                mapper.mapFromEntity(movie)
            }
        }

    }

    fun getMovie(movieId: Int): Single<Movie> {
        return api.getMovie(movieId)
            .map {
                mapper.mapFromEntity(it)
            }
    }

    fun save(movies: List<Movie>) {
        return db.save(movies.map {
            mapper.mapToEntity(it)
        })
    }
}

interface MoviesDataRepository {
    fun getMovies(): Observable<List<MovieEntity>>
    fun getMovie(movieId: Int): Single<MovieEntity>
    fun getFavourites(): Single<List<MovieEntity>>
    fun save(movies: List<MovieEntity>)
}