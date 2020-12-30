package ru.gressor.movies_browser.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.gressor.movies_browser.data.Movie
import ru.gressor.movies_browser.repo.MoviesRepo

class MoviesListVModel(
    private val moviesRepo: MoviesRepo
): ViewModel() {
    private val mutableMoviesList = MutableLiveData<List<Movie>>(emptyList())
    val moviesList : LiveData<List<Movie>> = mutableMoviesList

    fun loadMoviesList() {
        CoroutineScope(Dispatchers.Default).launch {
            mutableMoviesList.postValue(moviesRepo.getMovies())
        }
    }
}