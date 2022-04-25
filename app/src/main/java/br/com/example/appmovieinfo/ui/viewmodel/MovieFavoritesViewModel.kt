package br.com.example.appmovieinfo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import br.com.example.appmovieinfo.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieFavoritesViewModel @Inject
    constructor(
    private val repository: MovieRepository
): ViewModel(){
    val favoritesMovies = repository.allFavorites().asLiveData()
}