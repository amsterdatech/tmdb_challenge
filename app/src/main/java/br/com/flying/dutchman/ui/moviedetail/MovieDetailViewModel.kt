package br.com.flying.dutchman.ui.moviedetail

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import br.com.flying.dutchman.App
import br.com.flying.dutchman.ui.common.Movie
import br.com.flying.dutchman.ui.common.MovieWrapper
import br.com.flying.dutchman.ui.common.ViewState
import br.com.flying.dutchman.ui.common.livedata.LiveEvent
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDetailViewModel : ViewModel() {
    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }
    private val state: MutableLiveData<ViewState<Movie>> by lazy {
        MutableLiveData<ViewState<Movie>>()
    }

    private val removeFavouriteAction: MutableLiveData<LiveEvent<ViewState<Unit>>> =
        MutableLiveData()
    private val addFavouriteAction: MutableLiveData<LiveEvent<ViewState<Unit>>> = MutableLiveData()

    fun state() = state

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun loadMovieDetail(movieId:Long) {
        if (state.value == null) {
            state.postValue(ViewState(status = ViewState.Status.LOADING))

            Observable
                .defer {
                    Observable.just(Gson().fromJson(App.moviesJson(), MovieWrapper::class.java))
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        state.postValue(
                            ViewState(
                                status = ViewState.Status.SUCCESS,
                                data = it.data[0]
                            )
                        )
                    },
                    {
                        state.postValue(
                            ViewState(
                                status = ViewState.Status.ERROR
                            )
                        )
                    }
                )
                .apply {
                    compositeDisposable.add(this)
                }
        }
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}