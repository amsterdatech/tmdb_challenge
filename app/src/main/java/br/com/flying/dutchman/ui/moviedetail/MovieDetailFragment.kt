package br.com.flying.dutchman.ui.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import br.com.flying.dutchman.BuildConfig
import br.com.flying.dutchman.R
import br.com.flying.dutchman.ui.common.ViewState
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import kotlinx.android.synthetic.main.movie_details.*
import load

class MovieDetailFragment : Fragment() {

    private lateinit var moviesViewModel: MovieDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        moviesViewModel =
            ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_movie_detail, container, false)

        setHasOptionsMenu(true)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = arguments?.getLong("movie_id")

        setupView(view)

        moviesViewModel
            .state()
            .observe(this, Observer {
                when (it.status) {
                    ViewState.Status.LOADING -> {
                        this.fragment_movie_detail_custom_view_loading.visibility = View.VISIBLE
                    }

                    ViewState.Status.SUCCESS -> {
                        this.fragment_movie_detail_custom_view_loading.visibility = View.GONE

                        val movie = it.data

                        fragment_movies_detail_collapsing.title = movie?.title

                        val posterUrl = "${BuildConfig.IMAGE_SERVER}${movie?.posterPath}"
                        val backdropUrl = "${BuildConfig.IMAGE_SERVER}${movie?.backdrop}"

                        backdrop.load(backdropUrl)

                        poster_image.load(posterUrl)

                        title_text.text = movie?.title
                        info_text.text = "2019"
                        sinopsis.text =
                            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec at lorem lacinia ipsum venenatis pellentesque vitae vel sapien. Suspendisse mattis quam non leo pretium, vitae sodales lacus convallis. Mauris vitae vulputate dui. Nulla semper erat at massa molestie faucibus. Vestibulum lobortis sapien vitae eros condimentum, eget feugiat sem pretium. Quisque et sapien et nunc mattis tempor eget at magna. Sed in augue ex. Nullam sed dui ac odio semper sollicitudin. Praesent porttitor gravida ante sit amet ultricies. Nam sit amet lacus id mi facilisis mollis vitae sed nibh. Maecenas vehicula, massa id tincidunt pretium, ex mi egestas metus, sed semper tortor nisi ut massa.\n" +
                                    "\n" +
                                    "Donec tellus libero, iaculis eget semper id, sollicitudin at libero. Proin vitae turpis neque. Nunc et augue vitae ante vestibulum pretium. Pellentesque lobortis vel ipsum et lacinia. Phasellus gravida auctor metus ac sagittis. Aliquam a sapien quam. Fusce eu rutrum odio.\n" +
                                    "\n" +
                                    "Nam finibus diam sed quam cursus fermentum. Sed id ligula nunc. Nunc dapibus orci sed lorem dapibus tincidunt. Curabitur feugiat nisl sem, vitae vestibulum lacus auctor a. Vivamus sit amet orci non augue varius egestas. Integer eu porta est, eu ultrices purus. Fusce quam urna, suscipit ut sem id, finibus pulvinar ligula. Suspendisse sed condimentum est. Nam scelerisque nisi a lectus feugiat tristique.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec at lorem lacinia ipsum venenatis pellentesque vitae vel sapien. Suspendisse mattis quam non leo pretium, vitae sodales lacus convallis. Mauris vitae vulputate dui. Nulla semper erat at massa molestie faucibus. Vestibulum lobortis sapien vitae eros condimentum, eget feugiat sem pretium. Quisque et sapien et nunc mattis tempor eget at magna. Sed in augue ex. Nullam sed dui ac odio semper sollicitudin. Praesent porttitor gravida ante sit amet ultricies. Nam sit amet lacus id mi facilisis mollis vitae sed nibh. Maecenas vehicula, massa id tincidunt pretium, ex mi egestas metus, sed semper tortor nisi ut massa.\n" +
                                    "\n" +
                                    "Donec tellus libero, iaculis eget semper id, sollicitudin at libero. Proin vitae turpis neque. Nunc et augue vitae ante vestibulum pretium. Pellentesque lobortis vel ipsum et lacinia. Phasellus gravida auctor metus ac sagittis. Aliquam a sapien quam. Fusce eu rutrum odio.\n" +
                                    "\n" +
                                    "Nam finibus diam sed quam cursus fermentum. Sed id ligula nunc. Nunc dapibus orci sed lorem dapibus tincidunt. Curabitur feugiat nisl sem, vitae vestibulum lacus auctor a. Vivamus sit amet orci non augue varius egestas. Integer eu porta est, eu ultrices purus. Fusce quam urna, suscipit ut sem id, finibus pulvinar ligula. Suspendisse sed condimentum est. Nam scelerisque nisi a lectus feugiat tristique.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec at lorem lacinia ipsum venenatis pellentesque vitae vel sapien. Suspendisse mattis quam non leo pretium, vitae sodales lacus convallis. Mauris vitae vulputate dui. Nulla semper erat at massa molestie faucibus. Vestibulum lobortis sapien vitae eros condimentum, eget feugiat sem pretium. Quisque et sapien et nunc mattis tempor eget at magna. Sed in augue ex. Nullam sed dui ac odio semper sollicitudin. Praesent porttitor gravida ante sit amet ultricies. Nam sit amet lacus id mi facilisis mollis vitae sed nibh. Maecenas vehicula, massa id tincidunt pretium, ex mi egestas metus, sed semper tortor nisi ut massa.\n" +
                                    "\n" +
                                    "Donec tellus libero, iaculis eget semper id, sollicitudin at libero. Proin vitae turpis neque. Nunc et augue vitae ante vestibulum pretium. Pellentesque lobortis vel ipsum et lacinia. Phasellus gravida auctor metus ac sagittis. Aliquam a sapien quam. Fusce eu rutrum odio.\n" +
                                    "\n" +
                                    "Nam finibus diam sed quam cursus fermentum. Sed id ligula nunc. Nunc dapibus orci sed lorem dapibus tincidunt. Curabitur feugiat nisl sem, vitae vestibulum lacus auctor a. Vivamus sit amet orci non augue varius egestas. Integer eu porta est, eu ultrices purus. Fusce quam urna, suscipit ut sem id, finibus pulvinar ligula. Suspendisse sed condimentum est. Nam scelerisque nisi a lectus feugiat tristique.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec at lorem lacinia ipsum venenatis pellentesque vitae vel sapien. Suspendisse mattis quam non leo pretium, vitae sodales lacus convallis. Mauris vitae vulputate dui. Nulla semper erat at massa molestie faucibus. Vestibulum lobortis sapien vitae eros condimentum, eget feugiat sem pretium. Quisque et sapien et nunc mattis tempor eget at magna. Sed in augue ex. Nullam sed dui ac odio semper sollicitudin. Praesent porttitor gravida ante sit amet ultricies. Nam sit amet lacus id mi facilisis mollis vitae sed nibh. Maecenas vehicula, massa id tincidunt pretium, ex mi egestas metus, sed semper tortor nisi ut massa.\n" +
                                    "\n" +
                                    "Donec tellus libero, iaculis eget semper id, sollicitudin at libero. Proin vitae turpis neque. Nunc et augue vitae ante vestibulum pretium. Pellentesque lobortis vel ipsum et lacinia. Phasellus gravida auctor metus ac sagittis. Aliquam a sapien quam. Fusce eu rutrum odio.\n" +
                                    "\n" +
                                    "Nam finibus diam sed quam cursus fermentum. Sed id ligula nunc. Nunc dapibus orci sed lorem dapibus tincidunt. Curabitur feugiat nisl sem, vitae vestibulum lacus auctor a. Vivamus sit amet orci non augue varius egestas. Integer eu porta est, eu ultrices purus. Fusce quam urna, suscipit ut sem id, finibus pulvinar ligula. Suspendisse sed condimentum est. Nam scelerisque nisi a lectus feugiat tristique.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec at lorem lacinia ipsum venenatis pellentesque vitae vel sapien. Suspendisse mattis quam non leo pretium, vitae sodales lacus convallis. Mauris vitae vulputate dui. Nulla semper erat at massa molestie faucibus. Vestibulum lobortis sapien vitae eros condimentum, eget feugiat sem pretium. Quisque et sapien et nunc mattis tempor eget at magna. Sed in augue ex. Nullam sed dui ac odio semper sollicitudin. Praesent porttitor gravida ante sit amet ultricies. Nam sit amet lacus id mi facilisis mollis vitae sed nibh. Maecenas vehicula, massa id tincidunt pretium, ex mi egestas metus, sed semper tortor nisi ut massa.\n" +
                                    "\n" +
                                    "Donec tellus libero, iaculis eget semper id, sollicitudin at libero. Proin vitae turpis neque. Nunc et augue vitae ante vestibulum pretium. Pellentesque lobortis vel ipsum et lacinia. Phasellus gravida auctor metus ac sagittis. Aliquam a sapien quam. Fusce eu rutrum odio.\n" +
                                    "\n" +
                                    "Nam finibus diam sed quam cursus fermentum. Sed id ligula nunc. Nunc dapibus orci sed lorem dapibus tincidunt. Curabitur feugiat nisl sem, vitae vestibulum lacus auctor a. Vivamus sit amet orci non augue varius egestas. Integer eu porta est, eu ultrices purus. Fusce quam urna, suscipit ut sem id, finibus pulvinar ligula. Suspendisse sed condimentum est. Nam scelerisque nisi a lectus feugiat tristique.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec at lorem lacinia ipsum venenatis pellentesque vitae vel sapien. Suspendisse mattis quam non leo pretium, vitae sodales lacus convallis. Mauris vitae vulputate dui. Nulla semper erat at massa molestie faucibus. Vestibulum lobortis sapien vitae eros condimentum, eget feugiat sem pretium. Quisque et sapien et nunc mattis tempor eget at magna. Sed in augue ex. Nullam sed dui ac odio semper sollicitudin. Praesent porttitor gravida ante sit amet ultricies. Nam sit amet lacus id mi facilisis mollis vitae sed nibh. Maecenas vehicula, massa id tincidunt pretium, ex mi egestas metus, sed semper tortor nisi ut massa.\n" +
                                    "\n" +
                                    "Donec tellus libero, iaculis eget semper id, sollicitudin at libero. Proin vitae turpis neque. Nunc et augue vitae ante vestibulum pretium. Pellentesque lobortis vel ipsum et lacinia. Phasellus gravida auctor metus ac sagittis. Aliquam a sapien quam. Fusce eu rutrum odio.\n" +
                                    "\n" +
                                    "Nam finibus diam sed quam cursus fermentum. Sed id ligula nunc. Nunc dapibus orci sed lorem dapibus tincidunt. Curabitur feugiat nisl sem, vitae vestibulum lacus auctor a. Vivamus sit amet orci non augue varius egestas. Integer eu porta est, eu ultrices purus. Fusce quam urna, suscipit ut sem id, finibus pulvinar ligula. Suspendisse sed condimentum est. Nam scelerisque nisi a lectus feugiat tristique.Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec at lorem lacinia ipsum venenatis pellentesque vitae vel sapien. Suspendisse mattis quam non leo pretium, vitae sodales lacus convallis. Mauris vitae vulputate dui. Nulla semper erat at massa molestie faucibus. Vestibulum lobortis sapien vitae eros condimentum, eget feugiat sem pretium. Quisque et sapien et nunc mattis tempor eget at magna. Sed in augue ex. Nullam sed dui ac odio semper sollicitudin. Praesent porttitor gravida ante sit amet ultricies. Nam sit amet lacus id mi facilisis mollis vitae sed nibh. Maecenas vehicula, massa id tincidunt pretium, ex mi egestas metus, sed semper tortor nisi ut massa.\n" +
                                    "\n" +
                                    "Donec tellus libero, iaculis eget semper id, sollicitudin at libero. Proin vitae turpis neque. Nunc et augue vitae ante vestibulum pretium. Pellentesque lobortis vel ipsum et lacinia. Phasellus gravida auctor metus ac sagittis. Aliquam a sapien quam. Fusce eu rutrum odio.\n" +
                                    "\n" +
                                    "Nam finibus diam sed quam cursus fermentum. Sed id ligula nunc. Nunc dapibus orci sed lorem dapibus tincidunt. Curabitur feugiat nisl sem, vitae vestibulum lacus auctor a. Vivamus sit amet orci non augue varius egestas. Integer eu porta est, eu ultrices purus. Fusce quam urna, suscipit ut sem id, finibus pulvinar ligula. Suspendisse sed condimentum est. Nam scelerisque nisi a lectus feugiat tristique."

                    }

                    ViewState.Status.ERROR -> {
                        this.fragment_movie_detail_custom_view_loading.visibility = View.GONE
                        //show error view (try again)
                    }

                    ViewState.Status.COMPLETE -> {

                    }
                }
            })


        movieId?.let {
            moviesViewModel.loadMovieDetail(movieId)
        }

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