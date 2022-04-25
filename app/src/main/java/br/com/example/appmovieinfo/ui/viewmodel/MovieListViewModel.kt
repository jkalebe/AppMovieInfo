package br.com.example.appmovieinfo.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.example.appmovieinfo.model.Movie
import br.com.example.appmovieinfo.network.MovieService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val repository: MovieService) :
    ViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    fun loadMovies() {
        if (_state.value != null) return

        search("iron")

    }

    fun search(query: String) = viewModelScope.launch {
        _state.value = State.Loading
        repository.getMovie(query).let { result ->
            if (result.Search == null) {
                _state.value = State.Error(Exception("Error loading movies"), false)
            } else {
                _state.value = State.Loaded(result.Search)
            }
        }


    }


    sealed class State {
        object Loading : State()
        data class Loaded(val items: List<Movie>) : State()
        data class Error(val e: Throwable, var hasConsumed: Boolean) : State()
    }
}