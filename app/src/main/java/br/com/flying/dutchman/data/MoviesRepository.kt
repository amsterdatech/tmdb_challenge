package br.com.flying.dutchman.data

import android.util.Log
import br.com.flying.dutchman.data.cache.RoomRepository
import br.com.flying.dutchman.data.remote.ApiRepository
import br.com.flying.dutchman.domain.MoviesMapper
import com.dutchtechnologies.domain.Movie
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject


class MoviesRepository @Inject constructor(
    private val api: ApiRepository,
    private val db: RoomRepository,
    private val mapper: MoviesMapper
) {
    fun getMovies(): Observable<List<Movie>> {


        return api.getMovies().doOnNext { movies ->
            db.save(movies)
        }
            .map { list ->
                                list.map { movie ->
                    mapper.mapFromEntity(movie)
                }
            }

//        return db.getMovies().mergeWith(api.doOnNext { movies ->
//            db.save(movies)
//        })
//            .flatMap {
//                val movies = it.map { m ->
//                    mapper.mapFromEntity(m)
//                }
//                return@flatMap Observable.just(movies)
//            }
//            .doOnError {
//                Log.d("STRANGE THINGS ERROR", "DEMOGORGON@@")
//            }

//        return api.getMovies()
//            .map { list ->
//                list.map { movie ->
//                    mapper.mapFromEntity(movie)
//                }
//            }
    }
}

interface MoviesDataRepository {
    fun getMovies(): Observable<List<MovieEntity>>
    fun getMovie(movieId: Long): Single<MovieEntity>
    fun getFavourites(): Single<List<MovieEntity>>
    fun save(movies: List<MovieEntity>)
}