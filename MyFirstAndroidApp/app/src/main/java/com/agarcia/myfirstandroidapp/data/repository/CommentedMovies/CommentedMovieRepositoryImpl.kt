package com.agarcia.myfirstandroidapp.data.repository.CommentedMovies

import com.agarcia.myfirstandroidapp.data.database.dao.CommentedMovieDao
import com.agarcia.myfirstandroidapp.data.database.entities.CommentMovieEntity
import com.agarcia.myfirstandroidapp.data.database.entities.toDomain
import com.agarcia.myfirstandroidapp.data.model.CommentMovie
import com.agarcia.myfirstandroidapp.data.model.FavoriteMovie
import com.agarcia.myfirstandroidapp.data.model.toDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class CommentedMovieRepositoryImpl(
    private val commentedMovieDao: CommentedMovieDao,
) : CommentedMovieRepository {

    override fun getCommentedMovies(): Flow<List<CommentMovie>> {
        return commentedMovieDao.getCommentedMovies().map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun addCommentToMovie(
        movie: CommentMovieEntity
    ) {
        commentedMovieDao.addMovieCommented(movie)
    }

}