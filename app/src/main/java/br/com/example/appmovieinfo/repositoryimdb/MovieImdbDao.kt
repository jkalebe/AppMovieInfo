package br.com.example.appmovieinfo.repositoryimdb

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieImdbDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(movie: MovieImdbDB): Long

    @Delete
    suspend fun delete(movie: MovieImdbDB): Int

    @Query("SELECT * FROM MovieImdbDB")
    fun allFavorites(): Flow<List<MovieImdbDB>>

    @Query("SELECT COUNT(id) FROM MovieImdbDB WHERE id= :id")
    fun isFavorite(id: String): Int
}