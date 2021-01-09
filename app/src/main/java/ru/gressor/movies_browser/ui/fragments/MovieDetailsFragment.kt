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
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import kotlinx.coroutines.Dispatchers
import ru.gressor.movies_browser.R
import ru.gressor.movies_browser.entity.Movie
import ru.gressor.movies_browser.databinding.FragmentMovieDetailsBinding
import ru.gressor.movies_browser.repo.retrofit.RetrofitMoviesRepo
import ru.gressor.movies_browser.ui.adapters.ActorsRVAdapter
import ru.gressor.movies_browser.viewmodel.MovieDetailsVModel
import ru.gressor.movies_browser.viewmodel.MovieDetailsVModelFactory


class MovieDetailsFragment : Fragment() {
    private var listener: BackArrowListener? = null

    private lateinit var binding: FragmentMovieDetailsBinding
    private var adapter: ActorsRVAdapter? = null

    private val viewModel: MovieDetailsVModel by viewModels {
        val movie: Movie = arguments?.getParcelable(MOVIE_ARGUMENT)!!
        MovieDetailsVModelFactory(movie, RetrofitMoviesRepo(Dispatchers.Default))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.movieLiveData.observe(this.viewLifecycleOwner) {
            populateViews(it)
        }
        viewModel.loadMovie()

        binding.run {
            tvDetailsBackArrow.setOnClickListener {
                listener?.onBackArrowClicked()
            }
            rvDetailsCast.layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.HORIZONTAL, false
            )
            adapter = ActorsRVAdapter(viewModel.movieLiveData.value?.actors ?: emptyList())
            rvDetailsCast.adapter = adapter
        }
    }

    private fun makeItGray(imageView: ImageView) {
        val matrix = floatArrayOf(
            0.08f, 0.08f, 0.08f, 0f, 20f,
            0.08f, 0.08f, 0.08f, 0f, 20f,
            0.08f, 0.08f, 0.08f, 0f, 30f,
            0.00f, 0.00f, 0.00f, 1f, 0f
        )

        val colorMatrix = ColorMatrix(matrix)
        imageView.colorFilter = ColorMatrixColorFilter(colorMatrix)
    }

    private fun populateViews(movie: Movie) {
        val context = requireContext()

        binding.run {
            tvDetailsPg.text = context.getString(R.string.minimum_age_string, movie.minimumAge)
            tvDetailsTitle.text = movie.title
            tvDetailsGenres.text = movie.genres.joinToString { it.name }
            tvDetailsReviewsCount.text =
                context.getString(R.string.reviews_count, movie.numberOfRatings)
            rbDetailsRatingBar.rating = movie.ratings / 2
            tvDetailsStorylineText.text = movie.overview

            ivDetailsTitleImage.load(movie.backdrop) {
                error(R.drawable.ic_like)
            }
            makeItGray(ivDetailsTitleImage)
        }

        setUpActorsRecycler(movie)
    }

    private fun setUpActorsRecycler(movie: Movie) {
        if (movie.actors.isEmpty()) {
            binding.rvDetailsCast.visibility = View.GONE
            binding.detailsCast.visibility = View.GONE
        } else {
            binding.rvDetailsCast.visibility = View.VISIBLE
            binding.detailsCast.visibility = View.VISIBLE

            adapter?.actors = movie.actors
            adapter?.notifyDataSetChanged()
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