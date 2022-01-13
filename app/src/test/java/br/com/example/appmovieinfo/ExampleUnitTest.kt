package br.com.example.appmovieinfo

import br.com.example.appmovieinfo.modelimdb.MovieIMDBHttp
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun movieMovieApiTest() {
        val searchMovie = MovieIMDBHttp.searchMovieIMDB("iron man")
        searchMovie?.results?.forEach { movie ->
            println(movie)
        }
    }

    @Test
    fun movieDetail(){
        val movieDetail = MovieIMDBHttp.movieDetailIMDB("tt1258972")
        println(movieDetail)
    }
}