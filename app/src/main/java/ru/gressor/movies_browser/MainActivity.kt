package ru.gressor.movies_browser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), CardClickListener, BackArrowListener {

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

    override fun onCardClick() {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragment_container, MovieDetailsFragment())
            addToBackStack(null)
            commit()
        }
    }

    override fun onBackArrowClicked() {
        onBackPressed()
    }
}