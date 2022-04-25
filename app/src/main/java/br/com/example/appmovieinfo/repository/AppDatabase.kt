package br.com.example.appmovieinfo.repository

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MovieDB::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase() : RoomDatabase() {

    abstract  fun movieDao(): MovieDao
}