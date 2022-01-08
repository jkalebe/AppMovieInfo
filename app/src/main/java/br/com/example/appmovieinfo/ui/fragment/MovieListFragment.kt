package br.com.example.appmovieinfo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.example.appmovieinfo.R
import br.com.example.appmovieinfo.model.Movie
import br.com.example.appmovieinfo.ui.MovieDetailActivity
import br.com.example.appmovieinfo.ui.adapter.MovieListAdapter
import br.com.example.appmovieinfo.ui.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieListFragment : Fragment(){
    private val viewModel: MovieListViewModel by lazy {
        ViewModelProvider(this).get(MovieListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when(state){
                is MovieListViewModel.State.Loading ->
                    vwLoading.visibility = View.VISIBLE
                is MovieListViewModel.State.Loaded ->{
                    vwLoading.visibility = View.GONE
                    recyclerView.adapter = MovieListAdapter(state.items, this::openBookDetail)
                }
                is MovieListViewModel.State.Error ->{
                    vwLoading.visibility = View.GONE
                    if (!state.hasConsumed){
                        state.hasConsumed = true
                        Toast.makeText(requireContext(), R.string.error_loading, Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
        viewModel.loadMovies()

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null){
                    viewModel.search(query)
                    return true
                }
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if(query?.length!! >= 2){
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