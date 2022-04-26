package br.com.example.appmovieinfo.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.example.appmovieinfo.model.Movie
import br.com.example.appmovieinfo.model.MovieFullCast
import br.com.example.appmovieinfo.model.MovieInfo
import br.com.example.appmovieinfo.network.MovieService
import br.com.example.appmovieinfo.network.MovieServiceFullCast
import br.com.example.appmovieinfo.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val repositoryApi: MovieService,
    private val repositoryApiFull: MovieServiceFullCast
) : ViewModel() {

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    fun onCreate(movie: Movie) {
        viewModelScope.launch {
            _isFavorite.value = repository.isFavorite(movie.imdbID)
        }
    }

    fun saveToFavorites(movie: Movie) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.save(movie)
            }
            _isFavorite.value = repository.isFavorite(movie.imdbID)
        }
    }

    fun removeFromFavorites(movie: Movie) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.delete(movie)
            }
            _isFavorite.value = repository.isFavorite(movie.imdbID)
        }
    }

    fun loadMovieInfo(id: String) {
        if (_state.value != null) return
        viewModelScope.launch {
            _state.value = State.Loading
            repositoryApiFull.getMovieFullCast(id).let { movie ->
                if (movie == null) {
                    _state.value = State.Error(Exception("Error loading movie"), false)
                } else {
                    _state.value = State.Loaded(movie)
                }
            }
        }
    }

    sealed class State {
        object Loading : State()
        data class Loaded(val movie: MovieFullCast) : State()
        data class Error(val e: Throwable, var hasConsumed: Boolean) : State()
    }

}