package com.agarcia.myfirstandroidapp.data.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.agarcia.myfirstandroidapp.data.database.entities.FavoriteMovieEntity
import java.time.LocalDate

data class FavoriteMovie(
    val id: Int,
    val movieId: Int,
    val title: String,
    val posterUrl: String,
)

// Convert FavoriteMovie to FavoriteMovieEntity
fun FavoriteMovie.toDatabase(): FavoriteMovieEntity {
    return FavoriteMovieEntity(
        id = id,
        movieId = movieId,
        title = title,
        posterUrl = posterUrl
    )
}

// Convert FavoriteMovie to Movie
fun FavoriteMovieEntity.toDomain(): FavoriteMovie {
    return FavoriteMovie(
        id = id,
        movieId = movieId,
        title = title,
        posterUrl = posterUrl
    )
}

// Convert FavoriteMovie to Movie
@RequiresApi(Build.VERSION_CODES.O)
fun FavoriteMovie.toMovie(): Movie {
    return Movie(
        id = movieId,
        title = title,
        originalTitle = "",
        originalLanguage = "",
        overview = "",
        releaseDate = LocalDate.now().toString(),
        adult = false,
        genreIds = emptyList(),
        popularity = 0.0,
        voteAverage = 0.0,
        voteCount = 0,
        video = false,
        backdropUrl = "",
        posterUrl = posterUrl,
    )
}