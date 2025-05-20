package com.agarcia.myfirstandroidapp.ui.screens.Favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.agarcia.myfirstandroidapp.MyFirstAndroidAppAplication
import com.agarcia.myfirstandroidapp.data.model.FavoriteMovie
import com.agarcia.myfirstandroidapp.data.repository.FavoriteMovie.FavoriteMovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val favoritesRepository: FavoriteMovieRepository,
) : ViewModel() {

    private val _favorites = MutableStateFlow<List<FavoriteMovie>>(emptyList())
    val favorites: StateFlow<List<FavoriteMovie>> = _favorites

    init {
        viewModelScope.launch {
            getFavoriteMovies()
        }
    }

    suspend fun isFavoriteMovie(movieId: Int): Boolean {
        return favoritesRepository.isFavorite(movieId).first()
    }

    suspend fun getFavoriteMovies() {
        viewModelScope.launch {
            favoritesRepository.getFavoritesMovies().collect { movies ->
                _favorites.value = movies
            }
        }
    }

    fun addMovieToFavorites(movie: FavoriteMovie) {
        viewModelScope.launch {
            favoritesRepository.addMovieToFavorites(movie)
        }
    }

    fun removeMovieFromFavorites(movie: FavoriteMovie) {
        viewModelScope.launch {
            favoritesRepository.removeMovieFromFavorites(movie)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplication = this[APPLICATION_KEY] as MyFirstAndroidAppAplication
                FavoritesViewModel(aplication.appProvider.provideFavoriteMovieRepository())
            }
        }
    }
}