package br.com.flying.dutchman.ui.movies

import androidx.lifecycle.*
import br.com.flying.dutchman.App
import br.com.flying.dutchman.ui.movies.livedata.LiveEvent
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MoviesViewModel : ViewModel(), LifecycleObserver {

    val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }
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
                                data = it.data
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

    fun addFavourite() {}
    fun removeFavourite() {}

    fun addWatchList() {}
    fun removeWatchList() {}


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}