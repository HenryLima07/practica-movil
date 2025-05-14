package com.example.laboratorio005.views

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.laboratorio005.domain.Book
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BooksViewModel : ViewModel() {
    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    private val _books = MutableStateFlow<MutableList<Book>>(mutableListOf())
    val _message = MutableStateFlow<String?>(null)


    val isLoading: Boolean
        get() = _isLoading.value

    val books = _books.asStateFlow()

    val message = _message.asStateFlow()

    fun loadBooks() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                delay(3000)

                val newBooks = mutableListOf<Book>()
                for (i in 1..10) {
                    val book = Book(
                        title = "Luna de pluton",
                        author = "Droos"
                    )
                    newBooks.add(book)
                }

                _books.value = newBooks
                _message.value = "Datos cargados correctamente"
                println("Datos cargados correctamente")
            } catch (e: Exception) {
                println(e.message)
            } finally {
                _isLoading.value = false
            }
        }

    }

    fun clearMessage() {
        _message.value = null
    }
}