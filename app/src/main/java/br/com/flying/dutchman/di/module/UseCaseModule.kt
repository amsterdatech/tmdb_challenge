package br.com.flying.dutchman.di.module

import br.com.flying.dutchman.data.MoviesRepository
import com.dutchtechnologies.domain.interactor.GetMoviesListSingleUseCase
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class UseCaseModule {
    @Provides
    @Singleton
    @Named("ioScheduler")
    internal fun provideIoScheduler() = Schedulers.io()

    @Provides
    @Singleton
    @Named("mainThreadScheduler")
    internal fun provideMainThreadScheduler() = AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    internal fun provideGetMoviesListSingleUseCase(
        repository: MoviesRepository,
        @Named("ioScheduler") ioScheduler: Scheduler,
        @Named("mainThreadScheduler") mainThreadScheduler: Scheduler
    ): GetMoviesListSingleUseCase =

        GetMoviesListSingleUseCase(repository, ioScheduler, mainThreadScheduler)
}