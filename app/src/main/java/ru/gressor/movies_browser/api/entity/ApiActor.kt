package ru.gressor.movies_browser.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ApiActor(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("profile_path")
    val picture: String?
)