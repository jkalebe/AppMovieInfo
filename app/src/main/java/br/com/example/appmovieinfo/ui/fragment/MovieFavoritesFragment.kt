package br.com.example.appmovieinfo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.example.appmovieinfo.R
import br.com.example.appmovieinfo.model.Movie
import br.com.example.appmovieinfo.repository.MovieRepository
import br.com.example.appmovieinfo.ui.MovieDetailActivity
import br.com.example.appmovieinfo.ui.adapter.MovieListAdapter
import br.com.example.appmovieinfo.ui.viewmodel.MovieFavoritesViewModel
import br.com.example.appmovieinfo.ui.viewmodel.MovieVmFactory
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieFavoritesFragment : Fragment(){
    private val viewModel: MovieFavoritesViewModel by lazy {
        ViewModelProvider(
            this,
        MovieVmFactory(
            MovieRepository(requireContext())
        )).get(MovieFavoritesViewModel::class.java)
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
    
        val layoutManage = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManage
        recyclerView.addItemDecoration(
            DividerItemDecoration(requireContext(), layoutManage.orientation)
        )

        viewModel.favoritesMovies.observe(viewLifecycleOwner, Observer { items ->
            vwLoading.visibility = View.GONE
            recyclerView.adapter = MovieListAdapter(items, this::openBookDetail)
        })
        searchView.visibility = View.GONE
    }

    private fun openBookDetail(movie: Movie){
        MovieDetailActivity.open(requireContext(), movie)
    }
}