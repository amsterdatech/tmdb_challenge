package br.com.flying.dutchman.ui.common

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import br.com.flying.dutchman.BuildConfig
import br.com.flying.dutchman.R
import kotlinx.android.synthetic.main.view_holder_movie.view.*
import load
import kotlin.properties.Delegates

class MoviesAdapter(
    private val listener: OnItemClickListener<Movie, View>,
    var favouriteListener: OnItemClickFavouriteListener<Movie>? = null,
    var overflowListener: OnItemClickOverflowMenuListener<Movie, View>? = null
) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {


    var items: List<Movie> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_movie,
                parent,
                false
            )
        )


    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
            items[position],
            listener,
            favouriteListener,
            overflowListener
        )
    }


    class ViewHolder(var containerView: View) :
        RecyclerView.ViewHolder(containerView) {

        fun bind(
            item: Movie,
            listener: OnItemClickListener<Movie, View>,
            favouriteListener: OnItemClickFavouriteListener<Movie>?,
            overflowListener: OnItemClickOverflowMenuListener<Movie, View>?
        ) {
            containerView.custom_view_movie_title_text.text = item.title
            val posterUrl = "${BuildConfig.IMAGE_SERVER}${item.posterPath}"

            containerView.custom_view_movie_poster_image.load(posterUrl)

            containerView.favourite_btn.isChecked = item.isFavourite

            containerView.setOnClickListener {
                listener.onItemClicked(item, containerView.custom_view_movie_poster_image)
            }

            containerView.custom_view_movie_poster_image.setOnClickListener {
                listener.onItemClicked(item, containerView.custom_view_movie_poster_image)
            }

            containerView.favourite_btn.setOnClickListener {
                favouriteListener?.onItemFavouriteClicked(item)
            }

            containerView.overflow_btn.setOnClickListener {
                overflowListener?.onItemOverflowClicked(item, it)

            }
        }

    }

    interface OnItemClickListener<T, V> {
        fun onItemClicked(item: T, v: V)
    }

    interface OnItemClickFavouriteListener<T> {
        fun onItemFavouriteClicked(item: T)
    }

    interface OnItemClickOverflowMenuListener<T, View> {
        fun onItemOverflowClicked(item: T, v: View)
    }


}