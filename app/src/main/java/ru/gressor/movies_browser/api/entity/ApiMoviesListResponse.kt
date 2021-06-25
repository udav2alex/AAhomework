package ru.gressor.movies_browser.api.entity

import kotlinx.serialization.Serializable

@Serializable
data class ApiMoviesListResponse(
    val results: List<ApiMovie>
)