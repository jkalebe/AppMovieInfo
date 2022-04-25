package br.com.example.appmovieinfo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.example.appmovieinfo.databinding.FragmentMovieListBinding
import br.com.example.appmovieinfo.model.Movie
import br.com.example.appmovieinfo.ui.MovieDetailActivity
import br.com.example.appmovieinfo.ui.adapter.MovieListAdapter
import br.com.example.appmovieinfo.ui.viewmodel.MovieFavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFavoritesFragment : Fragment() {
    val viewModel: MovieFavoritesViewModel by viewModels()

    private var _binding: FragmentMovieListBinding? = null
    private val binding: FragmentMovieListBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =FragmentMovieListBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManage = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManage
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(requireContext(), layoutManage.orientation)
        )

        viewModel.favoritesMovies.observe(this, { items ->
            binding.vwLoading.root.visibility = View.GONE
            binding.recyclerView.adapter = MovieListAdapter(items, this::openBookDetail)
        })
        binding.searchView.visibility = View.GONE
    }

    private fun openBookDetail(movie: Movie) {
        MovieDetailActivity.open(requireContext(), movie)
    }
}