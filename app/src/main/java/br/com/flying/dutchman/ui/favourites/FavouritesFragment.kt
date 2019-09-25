package br.com.flying.dutchman.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
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
import javax.inject.Inject

class FavouritesFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var favouritesViewModel: FavouritesViewModel

    private val adapter by lazy {
        MoviesAdapter(object :
            MoviesAdapter.OnItemClickListener<Movie> {
            override fun onItemClicked(item: Movie) {
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favouritesViewModel =
            ViewModelProviders.of(this).get(FavouritesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_favourites_list, container, false)

        lifecycle.addObserver(favouritesViewModel)
        setHasOptionsMenu(true)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView(view)

        favouritesViewModel
            .state()
            .observe(this, Observer {
                when (it.status) {
                    ViewState.Status.LOADING -> {
                        this.fragment_movies_custom_view_loading.visibility = View.VISIBLE
                    }

                    ViewState.Status.SUCCESS -> {
                        this.fragment_movies_custom_view_loading.visibility = View.GONE

                        //update list in adapter
                        this.adapter.items = it.data ?: emptyList()
                        this.adapter.notifyDataSetChanged()

                    }

                    ViewState.Status.ERROR -> {
                        this.fragment_movies_custom_view_loading.visibility = View.GONE
                        //show error view (try again)
                    }

                    ViewState.Status.COMPLETE -> {

                    }
                }
            })

        favouritesViewModel.loadFavourites()
    }

    private fun setupView(view: View) {
        val navController = Navigation.findNavController(view)

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