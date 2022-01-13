package br.com.example.appmovieinfo.modelimdb

import br.com.example.appmovieinfo.model.SearchResult
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

object MovieIMDBHttp {

    private const val KEY_IMDB = "k_69cs00sg"
    private const val MOVIE_JSON_URL_IMDB = "https://imdb-api.com/en/API/SearchTitle/$KEY_IMDB/%s" /* aqui coloca o title filme*/
    private const val MOVIE_JSON_INFO_URL_IMDB = "https://imdb-api.com/en/API/Title/$KEY_IMDB/%s"

    private val client = OkHttpClient.Builder()
        .readTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(5, TimeUnit.SECONDS)
        .build()



    fun searchMovieIMDB(s:String): SearchResultImdb?{
        val request = Request.Builder()
            .url(String.format(MOVIE_JSON_URL_IMDB, s))
            .build()

        try {
            val response = client.newCall(request).execute()
            val json = response.body?.string()
            return Gson().fromJson(json, SearchResultImdb::class.java)
        }catch (e: Exception){
            e.printStackTrace()
        }
        return null
    }

    fun movieDetailIMDB(i:String): MovieImdbInfo?{
        val request = Request.Builder()
            .url(String.format(MOVIE_JSON_INFO_URL_IMDB, i))
            .build()

        try {
            val response = client.newCall(request).execute()
            val json = response.body?.string()
            return Gson().fromJson(json, MovieImdbInfo::class.java)
        }catch (e: Exception){
            e.printStackTrace()
        }
        return null
    }
}