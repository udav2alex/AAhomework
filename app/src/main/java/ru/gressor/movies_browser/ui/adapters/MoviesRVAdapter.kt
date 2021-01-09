package ru.gressor.movies_browser.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ru.gressor.movies_browser.R
import ru.gressor.movies_browser.entity.Movie
import ru.gressor.movies_browser.databinding.ViewHolderMovieBinding

class MoviesRVAdapter(
    private val movieClickListener: MovieClickListener,
    var movies: List<Movie>
) : RecyclerView.Adapter<MoviesRVAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder(
            ViewHolderMovieBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
        holder.itemView.setOnClickListener {
            movieClickListener.onMovieClick(movies[position])
        }
    }

    override fun getItemCount(): Int = movies.size

    class MovieViewHolder(
        private val binding: ViewHolderMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            movie.let {
                binding.run {
                    tvTitle.text = it.title
                    tvGenres.text = it.genres.joinToString { genre -> genre.name }
                    tvPg.text = context.getString(R.string.minimum_age_string, it.minimumAge)
                    tvReviews.text = context.getString(R.string.reviews_count, it.numberOfRatings)
                    rbRatingBar.rating = it.ratings / 2
                    tvDuration.text = context.getString(R.string.duration_min, it.runtime)

                    ivTitlePic.load(it.poster) {
                        error(R.drawable.ic_like)
                    }
                    binding.ivTitlePic.clipToOutline = true
                }

//                favorite.isChecked = movie.favorite
            }
        }
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

interface MovieClickListener {
    fun onMovieClick(movie: Movie)
}