package ru.gressor.movies_browser.ui.fragments

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import ru.gressor.movies_browser.R
import ru.gressor.movies_browser.entity.Movie
import ru.gressor.movies_browser.repo.retrofit.RetrofitMoviesRepo
import ru.gressor.movies_browser.ui.adapters.MovieClickListener
import ru.gressor.movies_browser.ui.adapters.MoviesRVAdapter
import ru.gressor.movies_browser.viewmodel.MoviesListVModel
import ru.gressor.movies_browser.viewmodel.MoviesListVModelFactory

class MoviesListFragment : Fragment() {
    private var listener: MovieClickListener? = null
    private var moviesRV: RecyclerView? = null
    private var moviesList: MutableList<Movie>? = mutableListOf()

    private val viewModel: MoviesListVModel by viewModels {
        MoviesListVModelFactory(RetrofitMoviesRepo(Dispatchers.Default))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesRV = view.findViewById(R.id.rv_movies)
        moviesRV?.run {
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(SpacesItemDecoration(11, 11))
            adapter = listener?.let { MoviesRVAdapter(it, moviesList!!) }
        }

        viewModel.moviesList.observe(this.viewLifecycleOwner) {
            updateList(it)
        }
        viewModel.loadMoviesList()
    }

    private fun updateList(list: List<Movie>) {
        moviesList?.clear()
        moviesList?.addAll(list)
        moviesRV?.adapter?.notifyDataSetChanged()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MovieClickListener) {
            listener = context
        }
    }

    override fun onDestroy() {
        listener = null
        moviesRV = null
        moviesList = null
        super.onDestroy()
    }
}


class SpacesItemDecoration(private val hSpace: Int, private val vSpace: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        val position: Int = parent.getChildAdapterPosition(view)

        if (position % 2 == 0) {
            outRect.right = parent.context.pxInDp(hSpace)
        }

        if (parent.getChildLayoutPosition(view) > 1) {
            outRect.top = parent.context.pxInDp(vSpace)
        }
    }

    private fun Context.pxInDp(dp: Int): Int = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), this.resources.displayMetrics
    ).toInt()
}