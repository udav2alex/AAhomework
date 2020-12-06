package ru.gressor.movies_browser.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Actor(
    val name: String,
    val avatar: String,
    val hasOscar: Boolean
): Parcelable {
}