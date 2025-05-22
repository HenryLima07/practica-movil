package com.agarcia.myfirstandroidapp.ui.screens.MovieList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.agarcia.myfirstandroidapp.MyFirstAndroidAppAplication
import com.agarcia.myfirstandroidapp.data.model.CommentMovie
import com.agarcia.myfirstandroidapp.data.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CommentedMoviesViewModel : ViewModel() {
    private val _commentedMovies = MutableStateFlow<List<CommentMovie>>(emptyList())
    val commentedMovies: StateFlow<List<CommentMovie>> = _commentedMovies

    fun addCommentToMovie(movie: Movie, review: String) {
        val comment = CommentMovie(
            id = _commentedMovies.value.size + 1,
            movieId = movie.id,
            title = movie.title,
            posterUrl = movie.posterUrl,
            reviews = review
        )

        viewModelScope.launch {
            val copy = _commentedMovies.value.toMutableList()
            copy.add(comment)
            _commentedMovies.value = copy
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplication = this[APPLICATION_KEY] as MyFirstAndroidAppAplication
                CommentedMoviesViewModel()
            }
        }
    }
}