package com.agarcia.myfirstandroidapp.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.agarcia.myfirstandroidapp.data.database.entities.CommentMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentedMovieDao {

    @Query("SELECT * FROM commented_movie")
    fun getCommentedMovies(): Flow<List<CommentMovieEntity>>

    @Query("SELECT * FROM commented_movie WHERE movieId = :movieId")
    fun getCommentedMovieById(movieId: Int): Flow<CommentMovieEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovieCommented(movie: CommentMovieEntity)

    @Delete
    suspend fun removeMovieCommented(movie: CommentMovieEntity)
}