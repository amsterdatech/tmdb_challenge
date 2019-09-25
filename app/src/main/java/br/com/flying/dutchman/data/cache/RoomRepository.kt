package br.com.flying.dutchman.data.cache

import br.com.flying.dutchman.data.MovieEntity
import br.com.flying.dutchman.data.MoviesDataRepository
import br.com.flying.dutchman.data.cache.database.AppDatabase
import br.com.flying.dutchman.data.cache.entity.Movie
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class RoomRepository @Inject constructor(
    private val db: AppDatabase,
    private val mapper: LocalEntityMapper<List<Movie>, List<MovieEntity>>
) :
    MoviesDataRepository {
    override fun getMovies(): Observable<List<MovieEntity>> {
        return db.movieDao().movies().map { movies ->
            mapper.mapFrom(movies)
        }
    }

    override fun getMovie(movieId: Long): Single<MovieEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFavourites(): Single<List<MovieEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}