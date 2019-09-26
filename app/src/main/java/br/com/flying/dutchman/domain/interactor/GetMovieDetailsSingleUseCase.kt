package br.com.flying.dutchman.domain.interactor

import com.dutchtechnologies.domain.interactor.SingleUseCase

import br.com.flying.dutchman.data.MoviesRepository
import com.dutchtechnologies.domain.Movie
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject

class GetMovieDetailsSingleUseCase @Inject constructor(
    private val repository: MoviesRepository,
    subscribeScheduler: Scheduler,
    postExecutionScheduler: Scheduler
) : SingleUseCase<Movie, Int>(subscribeScheduler, postExecutionScheduler) {

    override fun buildUseCaseSingle(params: Int?): Single<Movie> =
        repository.getMovie(params ?: 0)
}