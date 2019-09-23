package br.com.flying.dutchman.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.flying.dutchman.BuildConfig
import br.com.flying.dutchman.R
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.view_holder_movie.view.*
import load
import java.util.*
import kotlin.properties.Delegates

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    var items: List<Movie> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    lateinit var click: View.OnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_movie,
                parent,
                false
            ), click
        )


    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }


    class ViewHolder(var containerView: View, var onClick: View.OnClickListener? = null) :
        RecyclerView.ViewHolder(containerView) {

        fun bind(item: Movie) {
            containerView.custom_view_movie_title_text.text = item.title
            val posterUrl = "${BuildConfig.IMAGE_SERVER}${item.posterPath}"
            containerView.custom_view_movie_poster_image.load(posterUrl)
        }

    }


}