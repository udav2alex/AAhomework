package ru.gressor.movies_browser.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.gressor.movies_browser.entity.Movie
import ru.gressor.movies_browser.repo.MoviesRepo

class MovieDetailsVModelFactory(
    private val movie: Movie,
    private val moviesRepo: MoviesRepo
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MovieDetailsVModel::class.java ->
            MovieDetailsVModel(movie, moviesRepo)
        else ->
            throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}