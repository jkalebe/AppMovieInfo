package br.com.example.appmovieinfo.repository

import br.com.example.appmovieinfo.model.Movie
import br.com.example.appmovieinfo.model.MovieInfo

object MovieDBMovieMapper{
    fun movieDBToMovie(movieDB: MovieDB) =
        Movie(
            movieDB.Title,
            movieDB.Year,
            movieDB.id,
            movieDB.Type,
            movieDB.Poster
        )

    fun movieToMovieDB(movie: Movie) =
        movie.run {
            MovieDB(
                imdbID,
                Title,
                Year,
                Type,
                Poster
            )
        }
}