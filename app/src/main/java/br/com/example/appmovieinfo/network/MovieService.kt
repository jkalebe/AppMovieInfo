package br.com.example.appmovieinfo.network

import br.com.example.appmovieinfo.model.MovieHttp
import br.com.example.appmovieinfo.model.MovieInfo
import br.com.example.appmovieinfo.model.SearchResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieService @Inject constructor(private val api : MovieHttp){

    suspend fun getMovie(query: String):SearchResult{
        return withContext(Dispatchers.IO){
            api.getAllMovies(query)
        }
    }

    suspend fun getMovieByID(id: String): MovieInfo {
        return withContext(Dispatchers.IO){
            api.getDetailMovie(id)
        }
    }
}