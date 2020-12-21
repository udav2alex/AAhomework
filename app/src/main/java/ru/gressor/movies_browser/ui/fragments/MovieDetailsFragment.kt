package ru.gressor.movies_browser.ui.fragments

import android.content.Context
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.gressor.movies_browser.R
import ru.gressor.movies_browser.data.Movie
import ru.gressor.movies_browser.databinding.FragmentMovieDetailsBinding
import ru.gressor.movies_browser.ui.adapters.ActorsRVAdapter


class MovieDetailsFragment: Fragment() {
    private var listener: BackArrowListener? = null
    private var actorsRV: RecyclerView? = null
    private var movie: Movie? = null
    private lateinit var binding: FragmentMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movie = it.getParcelable(MOVIE_ARGUMENT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movie?.let { populateViews(it) }

        if(movie?.actors?.size ?: 0 > 0) {
            actorsRV = view.findViewById(R.id.rv_details_cast)
            actorsRV?.run {
                layoutManager = LinearLayoutManager(
                    requireContext(), LinearLayoutManager.HORIZONTAL, false
                )
                adapter = ActorsRVAdapter(movie?.actors ?: listOf())
            }
        } else {
            binding.rvDetailsCast.visibility = View.GONE
            binding.detailsCast.visibility = View.GONE
        }

        view.findViewById<View>(R.id.tv_details_back_arrow).setOnClickListener {
            listener?.onBackArrowClicked()
        }
    }

    private fun makeItGray(imageView: ImageView) {
        val matrix = floatArrayOf(
            0.08f, 0.08f, 0.08f, 0f, 20f,
            0.08f, 0.08f, 0.08f, 0f, 20f,
            0.08f, 0.08f, 0.08f, 0f, 30f,
            0.00f, 0.00f, 0.00f, 1f, 0f )

        val colorMatrix = ColorMatrix(matrix)
        imageView.colorFilter = ColorMatrixColorFilter(colorMatrix)
    }

    private fun populateViews(movie: Movie) {
        val context = requireContext()

        binding.run {
            tvDetailsPg.text = context.getString(R.string.minimum_age_string, movie.minimumAge)
            tvDetailsTitle.text = movie.title
            tvDetailsGenres.text = movie.genres.joinToString { it.name }
            tvDetailsReviewsCount.text = context.getString(R.string.reviews_count, movie.numberOfRatings)
            rbDetailsRatingBar.rating = movie.ratings / 2
            tvDetailsStorylineText.text = movie.overview

            Glide
                .with(context)
                .load(movie.backdrop)
                .into(ivDetailsTitleImage)
            makeItGray(ivDetailsTitleImage)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BackArrowListener) {
            listener = context
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        listener = null
    }

    companion object {
        private const val MOVIE_ARGUMENT = "MOVIE_ARGUMENT"

        fun newInstance(movie: Movie): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()

            val args = Bundle()
            args.putParcelable(MOVIE_ARGUMENT, movie)

            fragment.arguments = args
            return fragment
        }
    }
}

interface BackArrowListener {
    fun onBackArrowClicked()
}