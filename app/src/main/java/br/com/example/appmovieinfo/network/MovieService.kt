package br.com.example.appmovieinfo.network

import br.com.example.appmovieinfo.model.Movie
import br.com.example.appmovieinfo.model.MovieHttp
import br.com.example.appmovieinfo.model.MovieInfo
import br.com.example.appmovieinfo.model.SearchResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieService {
    private val service = RetrofitHelper.getRetrofit()
    suspend fun getMovie(query: String):SearchResult{
        return withContext(Dispatchers.IO){
            service.create(MovieHttp::class.java).getAllMovies(query)
        }
    }

    suspend fun getMovieByID(id: String): MovieInfo {
        return withContext(Dispatchers.IO){
            service.create(MovieHttp::class.java).getDetailMovie(id)
        }
    }
}