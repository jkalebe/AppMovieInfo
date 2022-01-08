package br.com.example.appmovieinfo.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.example.appmovieinfo.model.Movie
import br.com.example.appmovieinfo.model.MovieHttp
import br.com.example.appmovieinfo.model.MovieInfo
import br.com.example.appmovieinfo.repository.MovieDB
import br.com.example.appmovieinfo.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MovieDetailViewModel (
    private val repository: MovieRepository
    ): ViewModel(){
    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    fun onCreate(movie: Movie){
        viewModelScope.launch {
            _isFavorite.value = repository.isFavorite(movie.imdbID)
        }
    }

    fun saveToFavorites(movie: Movie){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.save(movie)
            }
            _isFavorite.value = repository.isFavorite(movie.imdbID)
        }
    }

    fun removeFromFavorites(movie: Movie){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                repository.delete(movie)
            }
            _isFavorite.value = repository.isFavorite(movie.imdbID)
        }
    }

    fun loadMovieInfo(id:String){
        if(_state.value != null) return

        viewModelScope.launch {
            _state.value = State.Loading
            val movie = withContext(Dispatchers.IO){
                MovieHttp.movieDetail(id)
            }
            if (movie == null){
                _state.value = State.Error(Exception("Error loading movie"), false)
            }else {
                _state.value = State.Loaded(movie)
            }
        }
    }
    sealed class State{
        object Loading: State()
        data class Loaded(val movie: MovieInfo): State()
        data class Error(val e: Throwable, var hasConsumed: Boolean): State()
    }

    }