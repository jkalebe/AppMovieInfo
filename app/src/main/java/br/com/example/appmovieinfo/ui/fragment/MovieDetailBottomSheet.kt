package br.com.example.appmovieinfo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import br.com.example.appmovieinfo.R
import br.com.example.appmovieinfo.databinding.BottomSheetMovieDetailBinding
import br.com.example.appmovieinfo.model.Backdrop
import br.com.example.appmovieinfo.model.Movie
import br.com.example.appmovieinfo.ui.viewmodel.MovieDetailViewModel
import com.example.dialog.CustomBottomSheet
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailBottomSheet : CustomBottomSheet() {
    private lateinit var binding: BottomSheetMovieDetailBinding
    private val viewModel: MovieDetailViewModel by viewModels<MovieDetailViewModel>()

    private val movie by lazy {
        arguments?.getParcelable(EXTRA_MOVIE) as? Movie ?: throw RuntimeException("No movie found ")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetMovieDetailBinding.inflate(layoutInflater)

        setupUI()
        return binding.root


    }

    fun setupUI(){

        viewModel.state.observe(this) { state ->
            when (state) {
                is MovieDetailViewModel.State.Loading ->
                    binding.vwLoading.root.visibility = View.VISIBLE
                is MovieDetailViewModel.State.Loaded -> {
                    binding.vwLoading.root.visibility = View.GONE
                    binding.detail.visibility = View.VISIBLE
                    val link = extractBackDrops(state.movie.posters.backdrops)
                    Picasso.get().load(link ?: state.movie.image).into(
                        binding.imgCover
                    )
                    Picasso.get().load(state.movie.image).into(
                        binding.imgCoverSmall
                    )
                    binding.txtTitle.text = state.movie.title
                    binding.txtType.text = state.movie.type
                    binding.txtYear.text = state.movie.year
                    binding.txtDescription.text = state.movie.plot
//                        txtRelease.text = state.movie.Released
                    binding.txtRunTime.text = state.movie.runtimeMins
                    binding.txtGenre.text = state.movie.genres
                    binding.txtDirector.text = state.movie.directors
//                        binding.txtWriter.text = state.movie.Writer
                    binding.txtActors.text = state.movie.actorList[0].name
                    binding.txtLanguage.text = state.movie.languages
                    binding.txtCountry.text = state.movie.countries
//                        binding.txtAwards.text = state.movie.Awards
//                        binding.txtBoxOffice.text = state.movie.BoxOffice
                }
                is MovieDetailViewModel.State.Error -> {
                    binding.vwLoading.root.visibility = View.GONE
                    if (!state.hasConsumed) {
                        state.hasConsumed = true
                        Toast.makeText(context, R.string.error_loading, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        viewModel.loadMovieInfo(movie.imdbID)

        viewModel.isFavorite.observe(
            viewLifecycleOwner
        ) { isFavorite ->
            if (isFavorite) {
                binding.fabFavorite.setImageResource(R.drawable.ic_delete)
                binding.fabFavorite.setOnClickListener {
                    viewModel.removeFromFavorites(movie)
                }
            } else {
                binding.fabFavorite.setImageResource(R.drawable.ic_add)
                binding.fabFavorite.setOnClickListener {
                    viewModel.saveToFavorites(movie)
                }
            }
        }
        viewModel.onCreate(movie)
    }

    fun extractBackDrops(listBackDrops: List<Backdrop>): String? {
        if(listBackDrops.isNotEmpty()){
           return listBackDrops[1].link
        }
        return null
    }

    companion object {
        private const val EXTRA_MOVIE= "movie"

        fun newInstance(movie: Movie): MovieDetailBottomSheet{
            return MovieDetailBottomSheet().apply {
                arguments = Bundle().apply {
                    putParcelable(EXTRA_MOVIE, movie)
                }
            }
        }
    }
}