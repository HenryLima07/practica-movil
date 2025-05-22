package com.agarcia.myfirstandroidapp.ui.screens.UpComming

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agarcia.myfirstandroidapp.ui.components.CommentedMovieItem
import com.agarcia.myfirstandroidapp.ui.screens.MovieList.CommentedMoviesViewModel

@Composable
fun UpComming(
    commentedMoviesViewModel: CommentedMoviesViewModel
) {
    val commentedMovies by commentedMoviesViewModel.commentedMovies.collectAsState()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Próximamente",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(commentedMovies) { commentedMovie ->
                CommentedMovieItem(commentedMovie)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}