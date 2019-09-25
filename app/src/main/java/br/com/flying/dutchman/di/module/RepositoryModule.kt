package br.com.flying.dutchman.di.module

import android.app.Application
import androidx.room.Room
import br.com.flying.dutchman.data.MoviesRepository
import br.com.flying.dutchman.data.cache.MovieLocalEntityMapper
import br.com.flying.dutchman.data.cache.RoomRepository
import br.com.flying.dutchman.data.cache.database.AppDatabase
import br.com.flying.dutchman.data.remote.ApiRepository
import br.com.flying.dutchman.data.remote.MovieRemoteEntityMapper
import br.com.flying.dutchman.data.remote.TmdbApiService
import br.com.flying.dutchman.domain.MoviesMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideMoviesRepository(
        mapper: MoviesMapper,
        api: ApiRepository,
        db: RoomRepository
    ): MoviesRepository = MoviesRepository(api, db, mapper)

    @Provides
    @Singleton
    fun provideApiRepository(
        service: TmdbApiService,
        mapper: MovieRemoteEntityMapper

    ): ApiRepository {
        return ApiRepository(service, mapper)
    }

    @Provides
    @Singleton
    fun provideRoomRepository(
        database: AppDatabase,
        mapper: MovieLocalEntityMapper
    ): RoomRepository {
        return RoomRepository(database, mapper)
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app.applicationContext,
            AppDatabase::class.java,
            "movies.db"
        )
            .build()
    }


}
