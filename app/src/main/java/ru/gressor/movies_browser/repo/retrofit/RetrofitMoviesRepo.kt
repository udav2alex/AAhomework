package ru.gressor.movies_browser.repo.retrofit

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.gressor.movies_browser.api.RetrofitHolder
import ru.gressor.movies_browser.api.entity.ApiGenre
import ru.gressor.movies_browser.api.entity.ApiMovie
import ru.gressor.movies_browser.entity.Actor
import ru.gressor.movies_browser.entity.Genre
import ru.gressor.movies_browser.entity.Movie
import ru.gressor.movies_browser.repo.MoviesRepo

class RetrofitMoviesRepo(
    private val coroutineDispatcher: CoroutineDispatcher
) : MoviesRepo {
    override suspend fun getMoviesNowPlaying(): List<Movie> = withContext(coroutineDispatcher) {
        val imageBaseUrl: String = getImageBaseUrl()
        val backBaseUrl = "$imageBaseUrl${RetrofitHolder.backSize}"
        val posterBaseUrl = "$imageBaseUrl${RetrofitHolder.posterSize}"

        val apiGenresList: List<ApiGenre> = getApiGenresList()

        RetrofitHolder.tmdbApi
            .getMoviesNowPlaying().results
            .map {
                movieApi2Domain(it, posterBaseUrl, backBaseUrl, apiGenresList)
            }
    }

    override suspend fun updateMovieRuntime(movie: Movie) = withContext(coroutineDispatcher) {
        movie.runtime = RetrofitHolder.tmdbApi
            .getMovieDetails(movie.id)
            .runtime
    }

    override suspend fun updateMovieActors(movie: Movie) = withContext(coroutineDispatcher) {
        val imageBaseUrl: String = getImageBaseUrl()
        val profileBaseUrl = "$imageBaseUrl${RetrofitHolder.profileSize}"

        movie.actors = RetrofitHolder.tmdbApi
            .getMovieCredits(movie.id)
            .apiMovieActors
            .map {
                Actor(it.id, it.name, "$profileBaseUrl${it.picture}")
            }
    }

    private suspend fun getImageBaseUrl() = RetrofitHolder.imagesBaseUrl
        ?: (RetrofitHolder.tmdbApi
            .getConfiguration()
            .apiConfigurationImages
            .secureBaseUrl
            .also {
                RetrofitHolder.imagesBaseUrl = it
            })

    private suspend fun getApiGenresList() = RetrofitHolder.apiGenresList
        ?: (RetrofitHolder.tmdbApi
            .getAllGenres()
            .genres
            .also {
                RetrofitHolder.apiGenresList = it
            })

    private fun movieApi2Domain(
        apiMovies: ApiMovie,
        posterBaseUrl: String,
        backBaseUrl: String,
        apiGenresList: List<ApiGenre>
    ) = with(apiMovies) {
        Movie(
            id,
            title,
            overview,
            "$posterBaseUrl$posterPath",
            "$backBaseUrl$backdropPath",
            voteAverage,
            voteCount,
            if (adult) 16 else 13,
            -1,                           // runtime, load later
            apiGenresList
                .filter { it.id in genreIds }
                .map { Genre(it.id, it.name) },
            emptyList()             // artists, load later
        )
    }
}