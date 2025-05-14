package com.example.laboratorio005.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.laboratorio005.views.components.BookCard
import kotlinx.coroutines.launch

@Composable
fun BooksView(viewModel: BooksViewModel, modifier: Modifier = Modifier) {

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val message = viewModel.message.collectAsState()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        LazyColumn(modifier = modifier.padding(innerPadding)) {
            item {
                Button(
                    onClick = { viewModel.loadBooks() },
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Get Data")
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            if (viewModel.isLoading)
                item {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
            if (!viewModel.isLoading)
                items(viewModel.books.value) { book ->
                    BookCard(book = book)
                }
        }
    }

    LaunchedEffect(message) {
        if (!message.value.isNullOrBlank()) {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message.value!!)
                viewModel.clearMessage()
            }
        }
    }
}