package br.com.example.appmovieinfo.repositoryimdb

import android.content.Context
import br.com.example.appmovieinfo.modelimdb.MovieIMDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class MovieRepositoryImdb(context: Context) {
    private val dao: MovieImdbDao = AppDatabase.getDatabase(context).getMovieDao()

    suspend fun save(movie: MovieIMDB){
        dao.save(MovieImdbDBMovieImdbMapper.movieToMovieDB(movie))
    }

    suspend fun delete(movie: MovieIMDB){
        dao.delete(MovieImdbDBMovieImdbMapper.movieToMovieDB(movie))
    }

    suspend fun isFavorite(id: String): Boolean{
        return withContext(Dispatchers.IO){
        dao.isFavorite(id) > 0
        }

    }

    fun allFavorites(): Flow<List<MovieIMDB>> {
        return dao.allFavorites()
            .map {movieList->
                movieList.map { movie->
                    MovieImdbDBMovieImdbMapper.movieDBToMovie(movie)
                }

            }
    }
}