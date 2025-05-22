package com.agarcia.myfirstandroidapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.agarcia.myfirstandroidapp.data.model.CommentMovie
import com.agarcia.myfirstandroidapp.data.model.FavoriteMovie

@Entity(tableName = "commented_movie")
data class CommentMovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val movieId: Int,
    val title: String,
    val posterUrl: String,
    val review: String
)

fun CommentMovieEntity.toDomain(): CommentMovie {
    return CommentMovie(
        id = id,
        movieId = movieId,
        title = title,
        posterUrl = posterUrl,
        reviews = review
    )
}