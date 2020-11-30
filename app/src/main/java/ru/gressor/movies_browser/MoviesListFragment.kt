package ru.gressor.movies_browser

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment

class MoviesListFragment: Fragment() {
    private var listener: CardClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ViewGroup>(R.id.movie_card_container).setOnClickListener {
            listener?.onCardClick()
        }

        view.findViewById<ImageView>(R.id.iv_movie_title_image).clipToOutline = true
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CardClickListener) {
            listener = context
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        listener = null
    }
}

interface CardClickListener {
    fun onCardClick()
}