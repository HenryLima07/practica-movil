package com.agarcia.myfirstandroidapp.ui.screens.MovieList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agarcia.myfirstandroidapp.data.model.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BottomSheetViewModel : ViewModel() {
    private val _showBottomSheet = MutableStateFlow(false)
    private val _reviewText = MutableStateFlow("")
    private val _selectedMovie = MutableStateFlow<Movie?>(null)

    val showBottomSheet: StateFlow<Boolean> = _showBottomSheet
    val reviewText: StateFlow<String> = _reviewText
    val selectedMovie: StateFlow<Movie?> = _selectedMovie


    fun openModal(movie: Movie?) {
        _showBottomSheet.value = true
        _selectedMovie.value = movie
    }

    fun updateReviewText(newText: String) {
        _reviewText.value = newText
    }

    fun onDismissRequest() {
        _showBottomSheet.value = false
        _selectedMovie.value = null
        _reviewText.value = ""
    }

}