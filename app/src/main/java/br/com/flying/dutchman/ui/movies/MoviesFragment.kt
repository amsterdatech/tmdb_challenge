package br.com.flying.dutchman.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import br.com.flying.dutchman.App
import br.com.flying.dutchman.R
import br.com.flying.dutchman.ui.common.GridSpacingItemDecoration
import br.com.flying.dutchman.ui.common.Movie
import br.com.flying.dutchman.ui.common.MoviesAdapter
import br.com.flying.dutchman.ui.common.ViewState
import dagger.android.support.DaggerFragment
import dpToPx
import kotlinx.android.synthetic.main.custom_loading_progressbar.*
import kotlinx.android.synthetic.main.fragment_movies_list.*
import javax.inject.Inject

class MoviesFragment : DaggerFragment(), MoviesAdapter.OnItemClickListener<Movie, View>,
    MoviesAdapter.OnItemClickOverflowMenuListener<Movie, View> {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var moviesViewModel: MoviesViewModel


    companion object {
        const val MOVIES_LIST = "MOVIES_LIST"
    }

    private val adapter by lazy {
        MoviesAdapter(
            listener = this,
            overflowListener = this
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_movies_list, container, false)
        setHasOptionsMenu(true)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView(view)
        observeMovies(moviesViewModel)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (adapter.items.isEmpty()) {
            savedInstanceState?.let {
                if (it.containsKey(MOVIES_LIST)) {
                    adapter.items = savedInstanceState.getParcelableArrayList(MOVIES_LIST)
                }
            }
        }
    }

    override fun onItemClicked(item: Movie, v: View) {
        val bundle = bundleOf("movie_id" to item.id)
        findNavController().navigate(
            R.id.navigate_to_movie_detail,
            bundle
        )
    }

    override fun onItemOverflowClicked(item: Movie, v: View) {
        val popMenu = PopupMenu(v.context, v)
        popMenu.inflate(R.menu.overflow_btn_menu)
        popMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.watch_list -> {
                    true
                }
            }
            false
        }
        popMenu.show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (adapter.items.isNotEmpty()) outState.putParcelableArrayList(
            MOVIES_LIST,
            adapter.items as ArrayList
        )
        super.onSaveInstanceState(outState)
    }


    private fun observeMovies(moviesViewModel: MoviesViewModel) {
        moviesViewModel
            .state()
            .observe(this, Observer {
                when (it.status) {
                    ViewState.Status.LOADING -> {
                        fragment_movies_custom_view_loading.visibility = View.VISIBLE
                    }

                    ViewState.Status.SUCCESS -> {
                        fragment_movies_custom_view_loading.visibility = View.GONE
                        val result = it.data ?: emptyList()
                        if (result.isNotEmpty()) {
                            this.adapter.items = result
                            this.adapter.notifyDataSetChanged()
                        } else {
                            // show empty state
                        }
                    }

                    ViewState.Status.ERROR -> {
                        fragment_movies_custom_view_loading.visibility = View.GONE
                        //show error view (try again)
                    }

                    ViewState.Status.COMPLETE -> {

                    }
                }
            })

        moviesViewModel.loadMovies()
    }


    private fun setupView(view: View) {
        val columnCount = view.resources.getInteger(R.integer.column_count)
        fragment_movies_recycler_view.layoutManager = GridLayoutManager(this.context, columnCount)

        fragment_movies_recycler_view.addItemDecoration(
            GridSpacingItemDecoration(
                columnCount,
                view.context.dpToPx(12),
                true
            )
        )
        fragment_movies_recycler_view.adapter = this.adapter
        fragment_movies_recycler_view.setHasFixedSize(true)
    }
}