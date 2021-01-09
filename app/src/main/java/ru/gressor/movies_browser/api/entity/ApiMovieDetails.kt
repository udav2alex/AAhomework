package ru.gressor.movies_browser.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiMovieDetails(
    @SerialName("runtime")
    val runtime: Int
)