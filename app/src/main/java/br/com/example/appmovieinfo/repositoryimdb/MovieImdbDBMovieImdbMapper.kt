package br.com.example.appmovieinfo.repositoryimdb

import br.com.example.appmovieinfo.modelimdb.MovieIMDB

object MovieImdbDBMovieImdbMapper{
    fun movieDBToMovie(movieDB: MovieImdbDB) =
        MovieIMDB(
            movieDB.id,
            movieDB.title,
            movieDB.description,
            movieDB.image
        )

    fun movieToMovieDB(movie: MovieIMDB) =
        movie.run {
            MovieImdbDB(id, title, description, image)
        }
}