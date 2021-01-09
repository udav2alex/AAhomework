package ru.gressor.movies_browser.repo.assets

import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.gressor.movies_browser.entity.Movie
import ru.gressor.movies_browser.entity.loadMovies
import ru.gressor.movies_browser.repo.MoviesRepo

class AssetsMoviesRepo(
    private val context: Context,
    private val coroutineDispatcher: CoroutineDispatcher
) : MoviesRepo {

    override suspend fun getMoviesNowPlaying(): List<Movie> =
        withContext(coroutineDispatcher) {
            loadMovies(context)
        }

    override suspend fun updateMovieRuntime(movie: Movie) {}

    override suspend fun updateMovieActors(movie: Movie) {}
}