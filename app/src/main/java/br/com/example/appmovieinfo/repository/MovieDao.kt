package br.com.example.appmovieinfo.repository

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(movie: MovieDB): Long

    @Delete
    suspend fun delete(movie: MovieDB): Int

    @Query("SELECT * FROM MovieDB")
    fun allFavorites(): Flow<List<MovieDB>>

    @Query("SELECT COUNT(id) FROM MovieDB WHERE id= :id")
    fun isFavorite(id: String): Int
}