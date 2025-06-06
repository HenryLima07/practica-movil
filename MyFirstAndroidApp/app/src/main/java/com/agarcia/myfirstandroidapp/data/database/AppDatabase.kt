package com.agarcia.myfirstandroidapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.agarcia.myfirstandroidapp.data.database.dao.CommentedMovieDao
import com.agarcia.myfirstandroidapp.data.database.dao.FavoriteMovieDao
import com.agarcia.myfirstandroidapp.data.database.entities.CommentMovieEntity
import com.agarcia.myfirstandroidapp.data.database.entities.FavoriteMovieEntity

@Database(
  entities = [FavoriteMovieEntity::class, CommentMovieEntity::class],
  version = 2,
  exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
  abstract fun favoriteMovieDao(): FavoriteMovieDao
  abstract fun commentedMovieDao(): CommentedMovieDao


  companion object {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
      return INSTANCE ?: synchronized(this) {
        val instance = Room.databaseBuilder(
          context = context.applicationContext,
          klass = AppDatabase::class.java,
          name = "movies_database"
        )
          .fallbackToDestructiveMigration(false)
          .build()
          .also {
            INSTANCE = it
          }
        instance
      }
    }
  }
}