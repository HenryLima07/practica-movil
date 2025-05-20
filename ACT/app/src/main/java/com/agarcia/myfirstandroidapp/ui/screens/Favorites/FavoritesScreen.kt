package com.agarcia.myfirstandroidapp.ui.screens.Favorites

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.agarcia.myfirstandroidapp.data.model.toMovie
import com.agarcia.myfirstandroidapp.ui.components.MovieItem
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Favorites(
    viewModel: FavoritesViewModel = viewModel(factory = FavoritesViewModel.Factory)
) {
    val favorites by viewModel.favorites.collectAsState()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn {
            if (viewModel.favorites.value.isEmpty())
                item {
                    Text("Mis Favoritos", style = MaterialTheme.typography.titleLarge)
                }

            items(favorites) { favorite ->
                MovieItem(
                    favorite.toMovie(),
                    handleFav = { viewModel.removeMovieFromFavorites(favorite) }) {

                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}