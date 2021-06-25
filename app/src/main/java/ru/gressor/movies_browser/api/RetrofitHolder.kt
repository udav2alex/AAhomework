package ru.gressor.movies_browser.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import ru.gressor.movies_browser.api.entity.ApiGenre

object RetrofitHolder {
    init {
        println("RetrofitHolder created!")
    }

    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }

    @Suppress("EXPERIMENTAL_API_USAGE")
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val newHttpUrl = chain.request().url.newBuilder()
                        .addQueryParameter("api_key", apiKey)
                        .build()
                    val newRequest = chain.request().newBuilder()
                        .url(newHttpUrl)
                        .build()
                    chain.proceed(newRequest)
                }
                .build()
        )
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    val tmdbApi: DataSource = retrofit.create(DataSource::class.java)

    var imagesBaseUrl: String? = null
    var backSize: String? = "w780"
    var posterSize: String? = "w780"
    var profileSize: String? = "w185"

    var apiGenresList: List<ApiGenre>? = null
}

const val BASE_URL = "https://api.themoviedb.org/3/"