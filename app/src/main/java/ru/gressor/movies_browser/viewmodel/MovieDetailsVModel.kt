package ru.gressor.movies_browser.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.gressor.movies_browser.data.Movie

class MovieDetailsVModel(
    movie: Movie
): ViewModel() {
    private val mutableMovieLiveData: MutableLiveData<Movie> = MutableLiveData(movie)
    val movieLiveData: LiveData<Movie> = mutableMovieLiveData
}