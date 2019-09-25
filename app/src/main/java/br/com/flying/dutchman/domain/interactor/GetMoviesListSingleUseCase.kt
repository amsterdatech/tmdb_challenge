package com.dutchtechnologies.domain.interactor

import br.com.flying.dutchman.data.MoviesRepository
import com.dutchtechnologies.domain.Movie
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class GetMoviesListSingleUseCase @Inject constructor(
    private val repository: MoviesRepository,
    subscribeScheduler: Scheduler,
    postExecutionScheduler: Scheduler
) : SingleUseCase<List<Movie>, Any?>(subscribeScheduler, postExecutionScheduler) {

    override fun buildUseCaseSingle(params: Any?): Single<List<Movie>> =
        Single.fromObservable(repository.getMovies())
}