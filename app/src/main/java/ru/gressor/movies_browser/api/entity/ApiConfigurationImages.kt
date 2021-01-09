package ru.gressor.movies_browser.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiConfigurationImages(
    @SerialName("secure_base_url")
    val secureBaseUrl: String
)