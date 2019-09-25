package br.com.flying.dutchman.data.cache.dao

import androidx.room.*
import br.com.flying.dutchman.data.cache.entity.Movie
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(movies: List<Movie>): Completable


    @Query("SELECT * FROM Movie")
    fun movies(): Observable<List<Movie>>


    @Query("SELECT * FROM Movie WHERE id = :id")
    fun movieById(id: String): Observable<Movie>
}