package ru.gressor.movies_browser

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class MovieDetailsFragment: Fragment() {
    private var listener: BackArrowListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.back_arrow).setOnClickListener {
            listener?.onBackArrowClicked()
        }
        view.findViewById<View>(R.id.back_arrow_text).setOnClickListener {
            listener?.onBackArrowClicked()
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
}

interface BackArrowListener {
    fun onBackArrowClicked()
}