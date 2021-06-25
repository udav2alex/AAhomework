package ru.gressor.movies_browser.api.entity

import kotlinx.serialization.Serializable

@Serializable
data class ApiGenre(
    val id: Int,
    val name: String
)