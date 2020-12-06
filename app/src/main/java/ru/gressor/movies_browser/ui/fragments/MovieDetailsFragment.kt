package ru.gressor.movies_browser.ui.fragments

import android.content.Context
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.gressor.movies_browser.R
import ru.gressor.movies_browser.entity.Movie
import ru.gressor.movies_browser.ui.adapters.ActorsRVAdapter


class MovieDetailsFragment: Fragment() {
    private var listener: BackArrowListener? = null
    private var actorsRV: RecyclerView? = null
    private var movie: Movie? = null

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
    ): View? = inflater.inflate(R.layout.fragment_movie_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movie?.let {
            populateViews(view, it)
            setTitleImage(view, it)
        }

        actorsRV = view.findViewById(R.id.rv_cast)
        actorsRV?.run {
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.HORIZONTAL, false
            )
            adapter = ActorsRVAdapter(movie?.cast ?: listOf())
        }

        view.findViewById<View>(R.id.iv_details_back_arrow).setOnClickListener {
            listener?.onBackArrowClicked()
        }
        view.findViewById<View>(R.id.tv_details_back_arrow_text).setOnClickListener {
            listener?.onBackArrowClicked()
        }
    }

    private fun setTitleImage(parent: View, movie: Movie) {
        val context = requireContext()
        val imageView = parent.findViewById<ImageView>(R.id.iv_details_title_image)

        Glide.with(context)
            .load(
                context.resources
                    .getIdentifier(movie.titlePic, "drawable", context.packageName)
            )
            .into(parent.findViewById(R.id.iv_details_title_image))

        val matrix = floatArrayOf(
            0.03f, 0.03f, 0.03f, 0f, 20f,
            0.03f, 0.03f, 0.03f, 0f, 20f,
            0.03f, 0.03f, 0.03f, 0f, 30f,
            0.00f, 0.00f, 0.00f, 1f, 0f )

        val colorMatrix = ColorMatrix(matrix)
        imageView.colorFilter = ColorMatrixColorFilter(colorMatrix)
    }

    private fun populateViews(parent: View, movie: Movie) {
        val context = requireContext()

        parent.findViewById<TextView>(R.id.tv_details_pg).text = movie.pg
        parent.findViewById<TextView>(R.id.tv_details_title).text = movie.title
        parent.findViewById<TextView>(R.id.tv_details_genres).text = movie.genres

        parent.findViewById<TextView>(R.id.tv_details_reviews_count).text =
            context.getString(R.string.reviews_count, movie.reviews)
        parent.findViewById<RatingBar>(R.id.rb_details_rating_bar).rating = movie.rating

        parent.findViewById<TextView>(R.id.tv_details_storyline_text).text = movie.storyline
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