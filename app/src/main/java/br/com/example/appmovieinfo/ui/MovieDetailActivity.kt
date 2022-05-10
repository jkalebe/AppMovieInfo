package br.com.example.appmovieinfo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.example.appmovieinfo.R
import br.com.example.appmovieinfo.databinding.ActivityMovieDetailBinding
import br.com.example.appmovieinfo.model.Movie
import br.com.example.appmovieinfo.ui.adapter.ActorListAdapter
import br.com.example.appmovieinfo.ui.viewmodel.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import android.view.WindowManager

import android.os.Build
import android.view.Window


@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private val viewModel: MovieDetailViewModel by viewModels<MovieDetailViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppImageView)
        // In Activity's onCreate() for instance
        // In Activity's onCreate() for instance
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val w: Window = window
            w.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val movie: Movie? = intent.getParcelableExtra(EXTRA_MOVIE)

        if (movie != null) {
            viewModel.state.observe(this, { state ->
                when (state) {
                    is MovieDetailViewModel.State.Loading -> {
                        binding.vwLoading.root.visibility = View.VISIBLE
                        binding.detail.visibility = View.GONE
                    }

                    is MovieDetailViewModel.State.Loaded -> {
                        binding.vwLoading.root.visibility = View.GONE
                        binding.detail.visibility = View.VISIBLE
                        binding.movie = state.movie

                        binding.rvActors.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
                        binding.rvActors.adapter = ActorListAdapter(state.movie.actorList)


                    }
                    is MovieDetailViewModel.State.Error -> {
                        binding.vwLoading.root.visibility = View.GONE
                        binding.detail.visibility = View.GONE
                        if (!state.hasConsumed) {
                            state.hasConsumed = true

                            Toast.makeText(this, R.string.error_loading, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })
            viewModel.loadMovieInfo(movie.imdbID)

//            viewModel.isFavorite.observe(
//                this,
//                Observer { isFavorite ->
//                    if(isFavorite){
//                        binding.fabFavorite.setImageResource(R.drawable.ic_delete)
//                        binding.fabFavorite.setOnClickListener {
//                            viewModel.removeFromFavorites(movie)
//                        }
//                    } else{
//                        binding.fabFavorite.setImageResource(R.drawable.ic_add)
//                        binding.fabFavorite.setOnClickListener {
//                            viewModel.saveToFavorites(movie)
//                        }
//                    }
//                }
//            )
//            viewModel.onCreate(movie)
        } else {
            finish()
        }
    }


    companion object {
        private const val EXTRA_MOVIE = "movie"

        fun open(context: Context, movie: Movie) {
            val detailIntent = Intent(context, MovieDetailActivity::class.java)
            detailIntent.putExtra(EXTRA_MOVIE, movie)
            context.startActivity(detailIntent)
        }
    }

}