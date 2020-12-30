package ru.gressor.movies_browser.repo.assets

import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.gressor.movies_browser.data.Movie
import ru.gressor.movies_browser.data.loadMovies
import ru.gressor.movies_browser.repo.MoviesRepo

class AssetsMoviesRepo(
    private val context: Context,
    private val coroutineDispatcher: CoroutineDispatcher
) : MoviesRepo {

    override suspend fun getMovies(): List<Movie> =
        withContext(coroutineDispatcher) {
            loadMovies(context)
        }
}