package ru.gressor.movies_browser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.gressor.movies_browser.*
import ru.gressor.movies_browser.data.Movie
import ru.gressor.movies_browser.ui.adapters.MovieClickListener
import ru.gressor.movies_browser.ui.fragments.BackArrowListener
import ru.gressor.movies_browser.ui.fragments.MovieDetailsFragment
import ru.gressor.movies_browser.ui.fragments.MoviesListFragment

class MainActivity : AppCompatActivity(), MovieClickListener, BackArrowListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                add(R.id.fragment_container, MoviesListFragment())
                commit()
            }
        }
    }

    override fun onMovieClick(movie: Movie) {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragment_container, MovieDetailsFragment.newInstance(movie))
            addToBackStack(null)
            commit()
        }
    }

    override fun onBackArrowClicked() {
        onBackPressed()
    }
}