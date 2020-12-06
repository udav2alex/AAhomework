package ru.gressor.movies_browser.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val title: String,
    val genres: String,
    val titlePic: String,
    var favorite: Boolean = false,
    val pg: String = "13+",
    val reviews: Int = 0,
    val rating: Float = 4.0f,
    val duration: Int = 90,
    val storyline: String = "But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and I will give you a complete account of the system, and expound the actual teachings of the great explorer of the truth, the master-builder of human happiness.",
    val cast: List<Actor> = listOf()
): Parcelable {
}