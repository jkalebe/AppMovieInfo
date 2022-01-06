package br.com.example.appmovieinfo.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import br.com.example.appmovieinfo.R
import br.com.example.appmovieinfo.model.Movie
import br.com.example.appmovieinfo.model.MovieHttp
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movieID = intent.getStringExtra(EXTRA_MOVIE)

        if (movieID != null){
            lifecycleScope.launch {
                val movie = withContext(Dispatchers.IO){
                    MovieHttp.movieDetail(movieID)
                }

                movie.let { movieInfo ->
                    if(movieInfo?.Poster != null){
                        Picasso.get().load(movieInfo.Poster).into(
                            imgCover
                        )
                    } else {
                        imgCover.setImageResource(R.drawable.ic_broken_image)
                    }

                    txtTitle.text = movieInfo?.Title
                    txtType.text = movieInfo?.Type
                    txtYear.text = movieInfo?.Year
                    txtDescription.text = movieInfo?.Plot
                    txtRelease.text = movieInfo?.Released
                    txtRunTime.text = movieInfo?.Runtime
                    txtGenre.text = movieInfo?.Genre
                    txtDirector.text = movieInfo?.Director
                    txtWriter.text = movieInfo?.Writer
                    txtActors.text = movieInfo?.Actors
                    txtLanguage.text = movieInfo?.Language
                    txtCountry.text = movieInfo?.Country
                    txtAwards.text = movieInfo?.Awards
                    txtBoxOffice.text = movieInfo?.BoxOffice
                 }
            }
        }

    }

    companion object {
        private const val EXTRA_MOVIE= "movie"

        fun open(context: Context, movie: Movie){
            val detailIntent = Intent(context, MovieDetailActivity::class.java)
            detailIntent.putExtra(EXTRA_MOVIE, movie.imdbID)
            context.startActivity(detailIntent)
        }
    }
}