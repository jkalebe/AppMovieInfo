package br.com.example.appmovieinfo.model

import br.com.example.appmovieinfo.di.Constants
import br.com.example.appmovieinfo.di.Constants.API_KEY_OMDB
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieHttp {

    @GET(API_KEY_OMDB)
    suspend fun getAllMovies(@Query("s") s:String):SearchResult

    @GET("?apikey=2ff0da89")
    suspend fun getDetailMovie(@Query("i") i:String):MovieInfo



}