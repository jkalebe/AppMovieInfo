package br.com.example.appmovieinfo.repository

import android.content.Context
import br.com.example.appmovieinfo.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepository @Inject constructor(private val dao: MovieDao) {

    suspend fun save(movie: Movie){
        dao.save(MovieDBMovieMapper.movieToMovieDB(movie))
    }

    suspend fun delete(movie: Movie){
        dao.delete(MovieDBMovieMapper.movieToMovieDB(movie))
    }

    suspend fun isFavorite(id: String): Boolean{
        return withContext(Dispatchers.IO){
        dao.isFavorite(id) > 0
        }

    }

    fun allFavorites(): Flow<List<Movie>> {
        return dao.allFavorites()
            .map {movieList->
                movieList.map { movie->
                    MovieDBMovieMapper.movieDBToMovie(movie)
                }

            }
    }
}