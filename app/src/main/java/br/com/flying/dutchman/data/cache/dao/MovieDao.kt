package br.com.flying.dutchman.data.cache.dao

import androidx.room.*
import br.com.flying.dutchman.data.cache.entity.Movie
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(movie: Movie)


    @Query("SELECT * FROM Movie")
    fun movies(): Observable<List<Movie>>

    @Query("SELECT * FROM Movie WHERE isFavourite = 1")
    fun favourites(): Observable<List<Movie>>

    @Query("SELECT * FROM Movie WHERE inWatchList = 1")
    fun watchList(): Observable<List<Movie>>


    @Query("SELECT * FROM Movie WHERE id = :id LIMIT 1")
    fun movieById(id: String): Observable<Movie>
}