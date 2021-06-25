package ru.gressor.movies_browser.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.gressor.movies_browser.api.entity.*

interface DataSource {
    @GET("configuration")
    suspend fun getConfiguration(): ApiConfigurationResponse

    @GET("genre/movie/list")
    suspend fun getAllGenres(): ApiGenresListResponse

    @GET("movie/now_playing")
    suspend fun getMoviesNowPlaying(): ApiMoviesListResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int): ApiMovieDetails

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(@Path("movie_id") movieId: Int): ApiMovieCredits
}