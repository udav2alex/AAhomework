package ru.gressor.movies_browser.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.gressor.movies_browser.R
import ru.gressor.movies_browser.data.Movie
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
                binding.tvTitle.text = it.title
                binding.tvGenres.text = it.genres.joinToString { genre -> genre.name }
                binding.tvPg.text = context.getString(R.string.minimum_age_string, it.minimumAge)
                binding.tvReviews.text = context.getString(R.string.reviews_count, it.numberOfRatings)
                binding.rbRatingBar.rating = it.ratings/2
                binding.tvDuration.text = context.getString(R.string.duration_min, it.runtime)

                Glide.with(context)
                    .load(it.poster)
                    .into(binding.ivTitlePic)
                binding.ivTitlePic.clipToOutline = true

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