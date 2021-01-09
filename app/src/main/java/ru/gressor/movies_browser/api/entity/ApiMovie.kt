package ru.gressor.movies_browser.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiMovie(
    @SerialName("id")
    val id: Int,
    @SerialName("genre_ids")
    val genreIds: List<Int>,
    @SerialName("title")
    val title: String = "",
    @SerialName("original_title")
    val originalTitle: String = "",
    @SerialName("original_language")
    val originalLanguage: String = "",
    @SerialName("poster_path")
    val posterPath: String = "",
    @SerialName("backdrop_path")
    val backdropPath: String = "",
    @SerialName("overview")
    val overview: String = "",
    @SerialName("popularity")
    val popularity: Float = 0f,
    @SerialName("vote_average")
    val voteAverage: Float = 0f,
    @SerialName("vote_count")
    val voteCount: Int = 0,
    @SerialName("video")
    val video: Boolean = false,
    @SerialName("release_date")
    val releaseDate: String = "",
    @SerialName("adult")
    val adult: Boolean = false
)