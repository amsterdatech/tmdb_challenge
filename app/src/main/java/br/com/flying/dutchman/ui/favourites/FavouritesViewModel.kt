package br.com.flying.dutchman.ui.favourites

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.flying.dutchman.App
import br.com.flying.dutchman.ui.common.Movie
import br.com.flying.dutchman.ui.common.MovieWrapper
import br.com.flying.dutchman.ui.common.ViewState
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FavouritesViewModel : ViewModel(), LifecycleObserver {

    private val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }
    private val state: MutableLiveData<ViewState<List<Movie>>> by lazy {
        MutableLiveData<ViewState<List<Movie>>>()
    }

    fun state() = state

    fun loadFavourites() {
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


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}