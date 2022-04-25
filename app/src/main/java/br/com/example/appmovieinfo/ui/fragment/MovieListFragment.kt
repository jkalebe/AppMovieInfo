package br.com.example.appmovieinfo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.example.appmovieinfo.R
import br.com.example.appmovieinfo.databinding.FragmentMovieListBinding
import br.com.example.appmovieinfo.model.Movie
import br.com.example.appmovieinfo.ui.MovieDetailActivity
import br.com.example.appmovieinfo.ui.adapter.MovieListAdapter
import br.com.example.appmovieinfo.ui.viewmodel.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment(){
    val viewModel : MovieListViewModel by viewModels()

    private var _binding: FragmentMovieListBinding? = null
    private val binding: FragmentMovieListBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMovieListBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when(state){
                is MovieListViewModel.State.Loading ->
                    binding.vwLoading.root.visibility = View.VISIBLE
                is MovieListViewModel.State.Loaded ->{
                    binding.vwLoading.root.visibility = View.GONE
                    binding.recyclerView.adapter = MovieListAdapter(state.items, this::openBookDetail)
                }
                is MovieListViewModel.State.Error ->{
                    binding.vwLoading.root.visibility = View.GONE
                    if (!state.hasConsumed){
                        state.hasConsumed = true
                        Toast.makeText(requireContext(), R.string.error_loading, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
        viewModel.loadMovies()

        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
//                if(query != null){
//                    viewModel.search(query)
//                    return true
//                }
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if(query != null){
                    viewModel.search(query)
                    return true
                }
                return false
            }

        })
    }

    private fun openBookDetail(movie: Movie){
        MovieDetailActivity.open(requireContext(), movie)
    }
}