package com.agarcia.myfirstandroidapp.ui.screens.MovieList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agarcia.myfirstandroidapp.data.model.Movie
import com.agarcia.myfirstandroidapp.ui.components.MovieBottomSheet
import com.agarcia.myfirstandroidapp.ui.components.MovieItem
import com.agarcia.myfirstandroidapp.ui.components.MoviePoster

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListScreen(
    onMovieClick: (Int) -> Unit = {},
    viewModel: MovieListViewModel = viewModel(factory = MovieListViewModel.Factory),
    commentedMoviesViewModel: CommentedMoviesViewModel
) {
    val movies by viewModel.movies.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val isLinearLayout by viewModel.isLinearLayout.collectAsState()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val bottomSheetViewModel = viewModel<BottomSheetViewModel>()

    val showBottomSheet by bottomSheetViewModel.showBottomSheet.collectAsState()
    val reviewText by bottomSheetViewModel.reviewText.collectAsState()
    val selectedMovie by bottomSheetViewModel.selectedMovie.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadMovies()
    }

    if (loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

        return
    }

    if (selectedMovie != null) {
        MovieBottomSheet(
            movie = selectedMovie!!,
            sheetState = sheetState,
            reviewText = reviewText,
            onDismissRequest = {
                bottomSheetViewModel.onDismissRequest()
            },
            onSaveReview = { movie, review ->
                commentedMoviesViewModel.addCommentToMovie(movie, review)
            },
            onReviewChange = { bottomSheetViewModel.updateReviewText(it) }
        )
    }

    // Control visibility here
    LaunchedEffect(showBottomSheet) {
        if (showBottomSheet) {
            sheetState.show()
        } else {
            sheetState.hide()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 0.dp, 16.dp, 16.dp)
    ) {
        Row() {
            Text(
                text = "Listado de peliculas",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.weight(1f))
            MovieListLayoutSwitch(
                isLinearLayout = isLinearLayout,
                onClick = { viewModel.changeLayoutPreferences(!isLinearLayout) }
            )
        }

        if (isLinearLayout) {
            MoviesLinearLayout(
                movies,
                onMovieClick
            ) { movie ->
                bottomSheetViewModel.openModal(movie)
            }
        } else {
            MoviesGridLayout(movies, onMovieClick, viewModel)
        }
    }
}

@Composable
fun MoviesLinearLayout(
    movies: List<Movie>,
    onMovieClick: (Int) -> Unit,
    setSelectedMovie: (Movie) -> Unit
) {
    LazyColumn() {
        items(movies) { movie ->
            MovieItem(
                movie = movie,
                onMovieClick = onMovieClick,
                onMovieSelected = { movie -> setSelectedMovie(movie) })
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun MoviesGridLayout(
    movies: List<Movie>,
    onMovieClick: (Int) -> Unit,
    viewModel: MovieListViewModel
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(0.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies) { movie ->
            val isFavorite by viewModel.isFavoriteMovie(movie.id).collectAsState(initial = false)
            MoviePoster(
                movie = movie,
                onMovieClick = onMovieClick,
                isFavorite = isFavorite,
                onFavoriteClick = { viewModel.toggleFavoriteMovie(movie) }
            )
        }
    }
}

@Composable
fun MovieListLayoutSwitch(isLinearLayout: Boolean, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
    ) {
        Icon(
            imageVector = if (isLinearLayout) {
                Icons.AutoMirrored.Filled.List
            } else {
                Icons.Default.GridView
            },
            contentDescription = "Change Layout"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MovieListScreenPreview() {
    val commentedMoviesViewModel = viewModel<CommentedMoviesViewModel>()
    MovieListScreen(commentedMoviesViewModel = commentedMoviesViewModel)
}