package ru.gressor.movies_browser.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiConfigurationResponse(
    @SerialName("images")
    val apiConfigurationImages: ApiConfigurationImages
)