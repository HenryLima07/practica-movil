package com.agarcia.myfirstandroidapp.ui.navigations

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.agarcia.myfirstandroidapp.ui.screens.Favorites.Favorites
import com.agarcia.myfirstandroidapp.ui.screens.MovieDetail.MovieDetailScreen
import com.agarcia.myfirstandroidapp.ui.screens.MovieList.CommentedMoviesViewModel
import com.agarcia.myfirstandroidapp.ui.screens.MovieList.MovieListScreen
import com.agarcia.myfirstandroidapp.ui.screens.UpComming.UpComming

@Composable
fun MainNavigation(navController: NavHostController) {
    val onMovieClick = { movieId: Int ->
        navController.navigate(MovieDetailScreenNavigation(movieId))
    }
    val commentedMoviesViewModel = viewModel<CommentedMoviesViewModel>()

    NavHost(navController = navController, startDestination = MovieListScreenNavigation) {
        composable<MovieListScreenNavigation> {
            MovieListScreen(onMovieClick, commentedMoviesViewModel = commentedMoviesViewModel)
        }
        composable<MovieDetailScreenNavigation> { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("id") ?: 0
            MovieDetailScreen(movieId = movieId)
        }
        composable<MyFavoritesScreenNavigation> {
            Favorites()
        }
        composable<UpCommingScreenNavigation> {
            UpComming(commentedMoviesViewModel)
        }
    }
}