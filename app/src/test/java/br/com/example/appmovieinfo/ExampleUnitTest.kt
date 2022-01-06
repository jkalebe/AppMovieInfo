package br.com.example.appmovieinfo

import br.com.example.appmovieinfo.model.MovieHttp
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun movieMovieApiTest() {
        val searchMovie = MovieHttp.searchMovie("iron man")
        searchMovie?.Search?.forEach { movie ->
            println(movie)
        }
    }

    @Test
    fun movieDetail(){
        val movieDetail = MovieHttp.movieDetail("tt1258972")
        println(movieDetail)
    }
}