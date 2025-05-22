package com.agarcia.myfirstandroidapp.data.model

import com.agarcia.myfirstandroidapp.data.database.entities.CommentMovieEntity

data class CommentMovie(
    val id: Int,
    val movieId: Int,
    val title: String,
    val posterUrl: String,
    val reviews: String
)

fun CommentMovie.toDatabase() = CommentMovieEntity(
    movieId = movieId,
    title = title,
    posterUrl = posterUrl,
    review = reviews
)


