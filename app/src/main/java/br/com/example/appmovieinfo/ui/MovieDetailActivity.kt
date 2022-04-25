package br.com.example.appmovieinfo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.example.appmovieinfo.R
import br.com.example.appmovieinfo.databinding.ActivityMovieDetailBinding
import br.com.example.appmovieinfo.model.Movie
import br.com.example.appmovieinfo.ui.viewmodel.MovieDetailViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMovieDetailBinding
    private val viewModel: MovieDetailViewModel by viewModels<MovieDetailViewModel>()
//    lazy {
//        ViewModelProvider(
//            this,
//            MovieVmFactory(
//                MovieRepository(this))
//        ).get(MovieDetailViewModel::class.java)
//    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie : Movie? = intent.getParcelableExtra(EXTRA_MOVIE)

        if (movie != null){
            viewModel.state.observe(this, Observer { state ->
                when(state){
                    is MovieDetailViewModel.State.Loading ->
                        binding.vwLoading.root.visibility = View.VISIBLE
                    is MovieDetailViewModel.State.Loaded ->{
                        binding.vwLoading.root.visibility = View.GONE
                        binding.detail.visibility = View.VISIBLE
                        Picasso.get().load(state.movie.Poster).into(
                            binding.imgCover
                        )
                        Picasso.get().load(state.movie.Poster).into(
                            binding.imgCoverSmall
                        )
                        binding.txtTitle.text = state.movie.Title
                        binding.txtType.text = state.movie.Type
                        binding.txtYear.text = state.movie.Year
                        binding.txtDescription.text = state.movie.Plot
//                        txtRelease.text = state.movie.Released
                        binding.txtRunTime.text = state.movie.Runtime
                        binding.txtGenre.text = state.movie.Genre
                        binding.txtDirector.text = state.movie.Director
//                        binding.txtWriter.text = state.movie.Writer
                        binding.txtActors.text = state.movie.Actors
                        binding.txtLanguage.text = state.movie.Language
                        binding.txtCountry.text = state.movie.Country
//                        binding.txtAwards.text = state.movie.Awards
//                        binding.txtBoxOffice.text = state.movie.BoxOffice
                    }
                    is MovieDetailViewModel.State.Error ->{
                        binding.vwLoading.root.visibility = View.GONE
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
                        binding.fabFavorite.setImageResource(R.drawable.ic_delete)
                        binding.fabFavorite.setOnClickListener {
                            viewModel.removeFromFavorites(movie)
                        }
                    } else{
                        binding.fabFavorite.setImageResource(R.drawable.ic_add)
                        binding.fabFavorite.setOnClickListener {
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