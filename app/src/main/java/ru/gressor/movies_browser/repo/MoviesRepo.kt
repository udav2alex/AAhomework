package ru.gressor.movies_browser.repo

import ru.gressor.movies_browser.entity.Movie

interface MoviesRepo {
    fun getMovies(): List<Movie>
}