package br.com.flying.dutchman.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import br.com.flying.dutchman.App
import br.com.flying.dutchman.R
import br.com.flying.dutchman.ui.common.GridSpacingItemDecoration
import br.com.flying.dutchman.ui.common.Movie
import br.com.flying.dutchman.ui.common.MoviesAdapter
import br.com.flying.dutchman.ui.common.ViewState
import dpToPx
import kotlinx.android.synthetic.main.custom_loading_progressbar.*
import kotlinx.android.synthetic.main.fragment_movies_list.*

class MoviesFragment : Fragment() {

    private lateinit var moviesViewModel: MoviesViewModel
    private val adapter by lazy {
        MoviesAdapter(object :
            MoviesAdapter.OnItemClickListener<Movie> {
            override fun onItemClicked(item: Movie) {
                val bundle = bundleOf("movie_id" to item.id)
                findNavController().navigate(R.id.navigate_to_movie_detail, bundle)
            }
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        moviesViewModel =
            ViewModelProviders.of(this).get(MoviesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_movies_list, container, false)

        lifecycle.addObserver(moviesViewModel)
        setHasOptionsMenu(true)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView(view)

        moviesViewModel
            .state()
            .observe(this, Observer {
                when (it.status) {
                    ViewState.Status.LOADING -> {
                        fragment_movies_custom_view_loading.visibility = View.VISIBLE
                    }

                    ViewState.Status.SUCCESS -> {
                        fragment_movies_custom_view_loading.visibility = View.GONE

                        //update list in adapter
                        this.adapter.items = it.data ?: emptyList()
                        this.adapter.notifyDataSetChanged()

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
        val navController = findNavController(view)

        fragment_movies_recycler_view.layoutManager = GridLayoutManager(this.context, 2)

        fragment_movies_recycler_view.addItemDecoration(
            GridSpacingItemDecoration(
                2,
                App.instance.dpToPx(12),
                true
            )
        )
        fragment_movies_recycler_view.adapter = this.adapter
        fragment_movies_recycler_view.setHasFixedSize(true)
    }

}