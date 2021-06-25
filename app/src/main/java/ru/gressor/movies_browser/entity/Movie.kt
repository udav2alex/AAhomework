package ru.gressor.movies_browser.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster: String,
    val backdrop: String,
    val ratings: Float,
    val numberOfRatings: Int,
    val minimumAge: Int,
    var runtime: Int,
    val genres: List<Genre>,
    var actors: List<Actor>
): Parcelable