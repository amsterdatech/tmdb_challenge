package br.com.flying.dutchman.ui.moviedetail

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import br.com.flying.dutchman.domain.interactor.GetMovieDetailsSingleUseCase
import br.com.flying.dutchman.ui.common.Movie
import br.com.flying.dutchman.ui.common.ViewState
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(private val movieDetailsUseCase: GetMovieDetailsSingleUseCase) :
    ViewModel() {
    private val state: MutableLiveData<ViewState<Movie>> by lazy {
        MutableLiveData<ViewState<Movie>>()
    }

    fun state() = state

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun loadMovieDetail(movieId: Int) {
        if (state.value == null) {
            state.postValue(ViewState(status = ViewState.Status.LOADING))
            movieDetailsUseCase.execute(MovieDetailsSubscriber(), movieId)
        }
    }


    override fun onCleared() {
        super.onCleared()
        movieDetailsUseCase.dispose()
    }

    inner class MovieDetailsSubscriber :
        DisposableSingleObserver<com.dutchtechnologies.domain.Movie>() {
        override fun onSuccess(result: com.dutchtechnologies.domain.Movie) {

            state.postValue(
                ViewState(
                    status = ViewState.Status.SUCCESS,
                    data = Movie(
                        result.id,
                        result.title,
                        result.overview,
                        result.backdropPath,
                        result.posterPath,
                        result.releaseData,
                        result.runtime,
                        result.voteAverage,
                        result.voteCount,
                        result.isFavourite,
                        result.inWatchList
                    )
                )
            )
        }

        override fun onError(e: Throwable) {
            state.postValue(
                ViewState(
                    status = ViewState.Status.ERROR, error = e
                )
            )
        }
    }

}