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
) : SingleUseCase<List<Movie>, Int>(subscribeScheduler, postExecutionScheduler) {

    override fun buildUseCaseSingle(params: Int?): Single<List<Movie>> {
        val page = params?.let {
            it
        }?:0

        if(page >0){
            Single.fromObservable(repository.getMovies(page))

        }

        return         Single.fromObservable(repository.getMovies())

    }
}