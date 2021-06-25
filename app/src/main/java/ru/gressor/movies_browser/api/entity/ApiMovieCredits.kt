package ru.gressor.movies_browser.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiMovieCredits(
    @SerialName("cast")
    val apiMovieActors: List<ApiActor>
)