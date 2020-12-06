package ru.gressor.movies_browser.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.gressor.movies_browser.R
import ru.gressor.movies_browser.entity.Movie
import ru.gressor.movies_browser.repo.stub.StubMoviesRepo

class MoviesRVAdapter(
    private val movieClickListener: MovieClickListener,
    var movies: List<Movie>
) : RecyclerView.Adapter<MoviesRVAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_movie, parent, false)
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
        holder.itemView.setOnClickListener {
            movieClickListener.onMovieClick(movies[position])
        }
    }

    override fun getItemCount(): Int = movies.size

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.tv_title)
        private val genres: TextView = itemView.findViewById(R.id.tv_details_genres)
        private val titlePic: ImageView = itemView.findViewById(R.id.iv_title_pic)
        private val favorite: CheckBox = itemView.findViewById(R.id.ch_favorite)
        private val pg: TextView = itemView.findViewById(R.id.tv_details_pg)
        private val reviews: TextView = itemView.findViewById(R.id.tv_reviews)
        private val rating: RatingBar = itemView.findViewById(R.id.rb_rating_bar)
        private val duration: TextView = itemView.findViewById(R.id.tv_duration)

        fun bind(movie: Movie) {
            movie.let {
                title.text = it.title
                genres.text = it.genres
                pg.text = it.pg
                reviews.text = context.getString(R.string.reviews_count, it.reviews)
                rating.rating = it.rating
                duration.text = context.getString(R.string.duration_min, it.duration)

                favorite.isChecked = movie.favorite
                favorite.setOnClickListener {
                    movie.favorite = favorite.isChecked
                }

                Glide.with(context)
                    .load(
                        context.resources
                            .getIdentifier(it.titlePic, "drawable", context.packageName)
                    )
                    .into(titlePic)

                titlePic.clipToOutline = true
            }
        }
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

interface MovieClickListener {
    fun onMovieClick(movie: Movie)
}