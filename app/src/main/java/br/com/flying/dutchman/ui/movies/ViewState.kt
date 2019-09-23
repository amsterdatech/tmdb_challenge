package br.com.flying.dutchman.ui.movies

class ViewState<D>(val status: Status, val data: D? = null, val error: Throwable? = null) {
    enum class Status {
        LOADING, SUCCESS, COMPLETE, ERROR
    }
}