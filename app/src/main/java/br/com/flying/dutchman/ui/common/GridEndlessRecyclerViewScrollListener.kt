package br.com.flying.dutchman.ui.common

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GridEndlessRecyclerViewScrollListener(
    private val gridLayoutManager: GridLayoutManager,
    private val dataLoader: DataLoader
) : RecyclerView.OnScrollListener() {
    private var previousItemCount: Int = 0
    private var loading: Boolean = false
    private var currentPage = 1


    init {
        reset()
    }


    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        if (dy > 0) {
            val itemCount = gridLayoutManager.itemCount

            if (itemCount != previousItemCount) {
                loading = false
            }

            if (!loading && gridLayoutManager.findLastVisibleItemPosition() >= itemCount - 1) {
                previousItemCount = itemCount
                dataLoader.onLoadMore(currentPage++)
                loading = true
            }
        }
    }

    fun reset() {
        this.loading = false
        this.previousItemCount = -1
    }

    interface DataLoader {
        fun onLoadMore(nextPage:Int): Boolean
    }
}