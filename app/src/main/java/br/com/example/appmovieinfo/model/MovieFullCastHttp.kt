package br.com.example.appmovieinfo.model

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieFullCastHttp {
    @GET("{id}/FullActor,FullCast,Posters,Images,Trailer,Ratings,Wikipedia,")
    suspend fun getFullCastMovie(@Path("id")id: String):MovieFullCast
}