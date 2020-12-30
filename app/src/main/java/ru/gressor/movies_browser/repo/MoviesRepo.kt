package ru.gressor.movies_browser.repo

import android.content.Context
import ru.gressor.movies_browser.data.Movie

interface MoviesRepo {
    suspend fun getMovies(): List<Movie>
}