package ru.gressor.movies_browser.repo

import ru.gressor.movies_browser.entity.Movie

interface MoviesRepo {
    suspend fun getMoviesNowPlaying(): List<Movie>
    suspend fun updateMovieRuntime(movie: Movie)
    suspend fun updateMovieActors(movie: Movie)
}