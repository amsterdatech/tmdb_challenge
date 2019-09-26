package br.com.flying.dutchman.ui.movies

import androidx.lifecycle.*
import br.com.flying.dutchman.App
import br.com.flying.dutchman.ui.common.Movie
import br.com.flying.dutchman.ui.common.MovieWrapper
import br.com.flying.dutchman.ui.common.ViewState
import br.com.flying.dutchman.ui.common.livedata.LiveEvent
import com.dutchtechnologies.domain.interactor.GetMoviesListSingleUseCase
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MoviesViewModel @Inject constructor(private val moviesUseCase: GetMoviesListSingleUseCase) :
    ViewModel(), LifecycleObserver {

    private val state: MutableLiveData<ViewState<List<Movie>>> by lazy {
        MutableLiveData<ViewState<List<Movie>>>()
    }

    private val removeFavouriteAction: MutableLiveData<LiveEvent<ViewState<Unit>>> =
        MutableLiveData()
    private val addFavouriteAction: MutableLiveData<LiveEvent<ViewState<Unit>>> = MutableLiveData()


    fun state() = state
    fun addFavouriteAction() = addFavouriteAction
    fun removeFavouriteAction() = removeFavouriteAction


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun loadMovies() {
        if (state.value == null) {
            state.postValue(ViewState(status = ViewState.Status.LOADING))
            moviesUseCase.execute(MoviesSubscriber())
        }
    }

    fun loadMovies(page: Int) {
        if (state.value == null) {
            state.postValue(ViewState(status = ViewState.Status.LOADING))
            moviesUseCase.execute(MoviesSubscriber(),page)
        }
    }


    override fun onCleared() {
        super.onCleared()
        moviesUseCase.dispose()
    }


    inner class MoviesSubscriber :
        DisposableSingleObserver<List<com.dutchtechnologies.domain.Movie>>() {
        override fun onSuccess(results: List<com.dutchtechnologies.domain.Movie>) {
            val result = results.map {
                Movie(
                    it.id,
                    it.title,
                    it.overview,
                    it.backdropPath,
                    it.posterPath,
                    it.releaseData,
                    it.runtime,
                    it.voteAverage,
                    it.voteCount,
                    it.isFavourite,
                    it.inWatchList
                )
            }

            state.postValue(
                ViewState(
                    status = ViewState.Status.SUCCESS,
                    data = result
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