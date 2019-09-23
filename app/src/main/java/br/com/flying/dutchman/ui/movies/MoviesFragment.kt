package br.com.flying.dutchman.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import br.com.flying.dutchman.App
import br.com.flying.dutchman.R
import dpToPx
import kotlinx.android.synthetic.main.fragment_movies_list.*

class MoviesFragment : Fragment() {

    private lateinit var moviesViewModel: MoviesViewModel
    private val adapter by lazy {
        MoviesAdapter()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        moviesViewModel =
            ViewModelProviders.of(this).get(MoviesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_movies_list, container, false)

        this.lifecycle.addObserver(moviesViewModel)
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

                    else -> {

                    }
                }
            })

        moviesViewModel.loadMovies()
    }

    private fun setupView(view: View) {
        fragment_movies_recycler_view.layoutManager = GridLayoutManager(this.context, 2)
        this.adapter.click = View.OnClickListener {

        }
        fragment_movies_recycler_view.addItemDecoration(
            GridSpacingItemDecoration(2, App.instance.dpToPx(12), true)
        )
        fragment_movies_recycler_view.adapter = this.adapter
        fragment_movies_recycler_view.setHasFixedSize(true)


    }

}