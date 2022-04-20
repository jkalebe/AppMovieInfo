package br.com.example.appmovieinfo.network

import br.com.example.appmovieinfo.model.Movie
import br.com.example.appmovieinfo.model.MovieInfo
import br.com.example.appmovieinfo.model.SearchResult

class MovieRepository  {

    private val service = MovieService()
    suspend fun getAllMovies(s:String):SearchResult{
        return service.getMovie(s)
    }

    suspend fun getDetailMovie(id:String): MovieInfo {
        return service.getMovieByID(id)
    }
}