package br.com.example.appmovieinfo.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import br.com.example.appmovieinfo.repository.MovieRepository

class MovieFavoritesViewModel(
    repository: MovieRepository
): ViewModel(){
    val favoritesMovies = repository.allFavorites().asLiveData()
}