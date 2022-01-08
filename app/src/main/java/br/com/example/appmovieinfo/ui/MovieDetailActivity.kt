package br.com.example.appmovieinfo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.example.appmovieinfo.R
import br.com.example.appmovieinfo.model.Movie
import br.com.example.appmovieinfo.repository.MovieRepository
import br.com.example.appmovieinfo.ui.viewmodel.MovieDetailViewModel
import br.com.example.appmovieinfo.ui.viewmodel.MovieVmFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity() {

    private val viewModel: MovieDetailViewModel by lazy {
        ViewModelProvider(
            this,
            MovieVmFactory(
                MovieRepository(this)
            )
        ).get(MovieDetailViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val movie : Movie? = intent.getParcelableExtra(EXTRA_MOVIE)

        if (movie != null){
            viewModel.state.observe(this, Observer { state ->
                when(state){
                    is MovieDetailViewModel.State.Loading ->
                        vwLoading.visibility = View.VISIBLE
                    is MovieDetailViewModel.State.Loaded ->{
                        vwLoading.visibility = View.GONE
                        detail.visibility = View.VISIBLE
                        Picasso.get().load(state.movie.Poster).into(
                            imgCover
                        )
                        Picasso.get().load(state.movie.Poster).into(
                            imgCoverSmall
                        )
                        txtTitle.text = state.movie.Title
                        txtType.text = state.movie.Type
                        txtYear.text = state.movie.Year
                        txtDescription.text = state.movie.Plot
                        txtRelease.text = state.movie.Released
                        txtRunTime.text = state.movie.Runtime
                        txtGenre.text = state.movie.Genre
                        txtDirector.text = state.movie.Director
                        txtWriter.text = state.movie.Writer
                        txtActors.text = state.movie.Actors
                        txtLanguage.text = state.movie.Language
                        txtCountry.text = state.movie.Country
                        txtAwards.text = state.movie.Awards
                        txtBoxOffice.text = state.movie.BoxOffice
                    }
                    is MovieDetailViewModel.State.Error ->{
                        vwLoading.visibility = View.GONE
                        if (!state.hasConsumed){
                            state.hasConsumed = true
                            Toast.makeText(this, R.string.error_loading, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })
            viewModel.loadMovieInfo(movie.imdbID)

            viewModel.isFavorite.observe(
                this,
                Observer { isFavorite ->
                    if(isFavorite){
                        fabFavorite.setImageResource(R.drawable.ic_delete)
                        fabFavorite.setOnClickListener {
                            viewModel.removeFromFavorites(movie)
                        }
                    } else{
                        fabFavorite.setImageResource(R.drawable.ic_add)
                        fabFavorite.setOnClickListener {
                            viewModel.saveToFavorites(movie)
                        }
                    }
                }
            )
            viewModel.onCreate(movie)
        } else{
            finish()
        }
        }



    companion object {
        private const val EXTRA_MOVIE= "movie"

        fun open(context: Context, movie: Movie){
            val detailIntent = Intent(context, MovieDetailActivity::class.java)
            detailIntent.putExtra(EXTRA_MOVIE, movie)
            context.startActivity(detailIntent)
        }
    }

}