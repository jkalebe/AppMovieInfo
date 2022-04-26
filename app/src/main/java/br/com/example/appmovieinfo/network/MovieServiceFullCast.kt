package br.com.example.appmovieinfo.network

import br.com.example.appmovieinfo.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class MovieServiceFullCast @Inject constructor(
    @Named("Full") private val apiIMDB: MovieFullCastHttp
) {
    suspend fun getMovieFullCast(id: String): MovieFullCast {
        return withContext(Dispatchers.IO) {
            apiIMDB.getFullCastMovie(id)
        }
    }
}