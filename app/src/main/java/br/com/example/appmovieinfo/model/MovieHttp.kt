package br.com.example.appmovieinfo.model

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception
import java.util.concurrent.TimeUnit

object MovieHttp {
    private const val KEY = "2ff0da89"
    private const val MOVIE_JSON_URL = "https://www.omdbapi.com/?apikey=$KEY&s=%s"
    private const val MOVIEINFO_JSON_URL = "https://www.omdbapi.com/?apikey=$KEY&i=%s"

    private const val KEY_IMDB = "k_69cs00sg"
    private const val MOVIE_JSON_URL_IMDB = "https://imdb-api.com/en/API/SearchTitle/$KEY_IMDB/%s" /* aqui coloca o title filme*/
    private const val MOVIE_JSON_INFO_URL_IMDB = "https://imdb-api.com/en/API/Title/$KEY_IMDB/%s"

    private val client = OkHttpClient.Builder()
        .readTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(5, TimeUnit.SECONDS)
        .build()

    fun searchMovie(s:String): SearchResult?{
        val request = Request.Builder()
            .url(String.format(MOVIE_JSON_URL, s))
            .build()

        try {
            val response = client.newCall(request).execute()
            val json = response.body?.string()
            return Gson().fromJson(json, SearchResult::class.java)
        }catch (e: Exception){
            e.printStackTrace()
        }
        return null
    }

    fun searchMovieIMDB(s:String): SearchResult?{
        val request = Request.Builder()
            .url(String.format(MOVIE_JSON_URL_IMDB, s))
            .build()

        try {
            val response = client.newCall(request).execute()
            val json = response.body?.string()
            return Gson().fromJson(json, SearchResult::class.java)
        }catch (e: Exception){
            e.printStackTrace()
        }
        return null
    }

    fun movieDetail(i:String): MovieInfo?{
        val request = Request.Builder()
            .url(String.format(MOVIEINFO_JSON_URL, i))
            .build()

        try {
            val response = client.newCall(request).execute()
            val json = response.body?.string()
            return Gson().fromJson(json, MovieInfo::class.java)
        }catch (e: Exception){
            e.printStackTrace()
        }
        return null
    }

    fun movieDetailIMDB(i:String): MovieInfo?{
        val request = Request.Builder()
            .url(String.format(MOVIE_JSON_INFO_URL_IMDB, i))
            .build()

        try {
            val response = client.newCall(request).execute()
            val json = response.body?.string()
            return Gson().fromJson(json, MovieInfo::class.java)
        }catch (e: Exception){
            e.printStackTrace()
        }
        return null
    }
}