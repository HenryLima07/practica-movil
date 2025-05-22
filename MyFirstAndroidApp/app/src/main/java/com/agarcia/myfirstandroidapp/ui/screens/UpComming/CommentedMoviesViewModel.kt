package com.agarcia.myfirstandroidapp.ui.screens.UpComming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.agarcia.myfirstandroidapp.MyFirstAndroidAppAplication
import com.agarcia.myfirstandroidapp.data.model.CommentMovie
import com.agarcia.myfirstandroidapp.data.model.FavoriteMovie
import com.agarcia.myfirstandroidapp.data.model.Movie
import com.agarcia.myfirstandroidapp.data.model.toDatabase
import com.agarcia.myfirstandroidapp.data.repository.CommentedMovies.CommentedMovieRepository
import com.agarcia.myfirstandroidapp.data.repository.Settings.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CommentedMoviesViewModel(
    private val commentedmovieRepository: CommentedMovieRepository,

    ) : ViewModel() {
    val commentedMovies: Flow<List<CommentMovie>> = commentedmovieRepository.getCommentedMovies()
    private val _loading = MutableStateFlow<Boolean>(false)

    fun addCommentToMovie(movie: Movie, review: String) {
        viewModelScope.launch {
            val comment = CommentMovie(
                id = movie.id,
                movieId = movie.id,
                title = movie.title,
                posterUrl = movie.posterUrl,
                reviews = review
            )
            commentedmovieRepository.addCommentToMovie(comment.toDatabase())
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val aplication =
                    this[ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY] as MyFirstAndroidAppAplication
                CommentedMoviesViewModel(
                    aplication.appProvider.provideCommentedMovieRepository()
                )
            }
        }
    }
}