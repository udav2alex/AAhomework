package ru.gressor.movies_browser.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.gressor.movies_browser.repo.MoviesRepo

class MoviesListVModelFactory(
    private val moviesRepo: MoviesRepo
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MoviesListVModel::class.java ->
            MoviesListVModel(moviesRepo)
        else ->
            throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}