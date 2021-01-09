package ru.gressor.movies_browser.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.gressor.movies_browser.api.entity.*

interface DataSource {
    @GET("configuration")
    suspend fun getConfiguration(
        @Query("api_key") apiKey: String
    ): ApiConfigurationResponse

    @GET("genre/movie/list")
    suspend fun getAllGenres(
        @Query("api_key") apiKey: String
    ): ApiGenresListResponse

    @GET("movie/now_playing")
    suspend fun getMoviesNowPlaying(
        @Query("api_key") apiKey: String
    ): ApiMoviesListResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): ApiMovieDetails

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): ApiMovieCredits
}