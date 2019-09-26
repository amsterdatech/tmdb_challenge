package br.com.flying.dutchman.ui.moviedetail

import android.os.Bundle
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import br.com.flying.dutchman.App
import br.com.flying.dutchman.BuildConfig
import br.com.flying.dutchman.R
import br.com.flying.dutchman.ui.common.Movie
import br.com.flying.dutchman.ui.common.ViewState
import dagger.android.support.DaggerFragment
import image
import kotlinx.android.synthetic.main.custom_loading_progressbar.*
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import kotlinx.android.synthetic.main.include_movie_details.*
import kotlinx.android.synthetic.main.include_toolbar_collapsing_movie_detail.*
import load
import toDate
import toString
import javax.inject.Inject

class MovieDetailFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var movieDetailsViewModel: MovieDetailViewModel


    var movieId: Int = 0


    companion object {
        const val MOVIE_ID = "MOVIE_ID"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_movie_detail, container, false)
        setHasOptionsMenu(true)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieId = arguments?.getInt("movie_id") ?: 0

        setupView(view)
        observeDetails(movieDetailsViewModel)

        movieId.let {
            movieDetailsViewModel.loadMovieDetail(movieId)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        savedInstanceState?.let {
            if (movieId == 0) {
                movieId = it.getInt(MOVIE_ID)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(MOVIE_ID, movieId)
        super.onSaveInstanceState(outState)
    }

    private fun observeDetails(movieDetailsViewModel: MovieDetailViewModel) {
        movieDetailsViewModel
            .state()
            .observe(this, Observer {
                when (it.status) {
                    ViewState.Status.LOADING -> {
                        fragment_movies_custom_view_loading.visibility = View.VISIBLE
                        fragment_movie_details_nested_scroll.visibility = View.GONE
                    }

                    ViewState.Status.SUCCESS -> {
                        bindDetails(it)
                        fragment_movies_custom_view_loading.visibility = View.GONE
                        fragment_movie_details_nested_scroll.visibility = View.VISIBLE

                    }

                    ViewState.Status.ERROR -> {
                        fragment_movies_custom_view_loading.visibility = View.GONE
                        fragment_movie_details_nested_scroll.visibility = View.GONE
                        //show error view (try again)
                    }
                    else -> {

                    }
                }
            })
    }

    private fun bindDetails(it: ViewState<Movie>) {
        val movie = it.data
        fragment_movies_detail_collapsing.title = movie?.title

        val posterUrl = "${BuildConfig.IMAGE_SERVER}${movie?.posterPath}"
        val backdropUrl = "${BuildConfig.IMAGE_SERVER}${movie?.backdrop}"


        val year = movie?.releaseData?.toDate("yyyy")?.toString("yyyy")
        val rating = movie?.voteAverage
        val additionalInfo = "  $rating - $year"

        val spanString = SpannableString(
            additionalInfo
        ).image(
            App.instance,
            R.drawable.ic_rating_black,
            0,
            1
        )

        backdrop.load(backdropUrl)
        poster_image.load(posterUrl)

        title.text = movie?.title
        info.text = spanString
        description.text = movie?.overview
    }

    private fun setupView(view: View) {
        val navController = Navigation.findNavController(view)
        NavigationUI.setupWithNavController(
            fragment_movies_detail_collapsing,
            movie_detail_toolbar,
            navController
        )

        fragment_movies_detail_collapsing.setExpandedTitleColor(resources.getColor(android.R.color.transparent))
    }
}