package com.agarcia.myfirstandroidapp.data.repository.CommentedMovies

import com.agarcia.myfirstandroidapp.data.database.entities.CommentMovieEntity
import com.agarcia.myfirstandroidapp.data.model.CommentMovie
import com.agarcia.myfirstandroidapp.data.model.FavoriteMovie
import kotlinx.coroutines.flow.Flow

interface CommentedMovieRepository {
    fun getCommentedMovies(): Flow<List<CommentMovie>>
    suspend fun addCommentToMovie(movie: CommentMovieEntity)
}