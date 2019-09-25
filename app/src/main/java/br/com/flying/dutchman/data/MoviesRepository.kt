package br.com.flying.dutchman.data

import br.com.flying.dutchman.data.cache.RoomRepository
import br.com.flying.dutchman.data.remote.ApiRepository
import br.com.flying.dutchman.domain.MoviesMapper
import com.dutchtechnologies.domain.Movie
import io.reactivex.Observable
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class MoviesRepository @Inject constructor(
    private val api: ApiRepository,
    private val db: RoomRepository,
    private val mapper: MoviesMapper
) {
    fun getMovies(): Observable<List<Movie>> {

        return api.getMovies()
            .map { list ->
                list.map { movie ->
                    mapper.mapFromEntity(movie)
                }
            }
    }
}

interface MoviesDataRepository {
    fun getMovies(): Observable<List<MovieEntity>>
    fun getMovie(movieId: Long): Single<MovieEntity>
    fun getFavourites(): Single<List<MovieEntity>>

}