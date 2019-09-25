package br.com.flying.dutchman.data.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.flying.dutchman.data.cache.dao.MovieDao
import br.com.flying.dutchman.data.cache.entity.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "booksDb"
                )
                    .build()
            }
            return instance as AppDatabase
        }

        fun destroyInstance() {
            instance = null
        }
    }
}