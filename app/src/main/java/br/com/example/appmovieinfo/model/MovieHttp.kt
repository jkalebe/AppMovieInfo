package br.com.example.appmovieinfo.model

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieHttp {

    @GET("?apikey=2ff0da89")
    suspend fun getAllMovies(@Query("s") s:String):SearchResult

    @GET("?apikey=2ff0da89")
    suspend fun getDetailMovie(@Query("i") i:String):MovieInfo

}