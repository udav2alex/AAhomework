package ru.gressor.movies_browser.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.gressor.movies_browser.entity.Movie
import ru.gressor.movies_browser.repo.MoviesRepo

class MovieDetailsVModel(
    private val movie: Movie,
    private val moviesRepo: MoviesRepo
): ViewModel() {
    private val mutableMovieLiveData: MutableLiveData<Movie> = MutableLiveData(movie)
    val movieLiveData: LiveData<Movie> = mutableMovieLiveData

    fun loadMovie() {
        CoroutineScope(Dispatchers.Default).launch {
            moviesRepo.updateMovieActors(movie)
            mutableMovieLiveData.postValue(movie)
        }
    }
}