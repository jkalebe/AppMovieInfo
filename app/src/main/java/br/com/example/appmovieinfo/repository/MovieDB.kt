package br.com.example.appmovieinfo.repository

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieDB(
    @PrimaryKey
    val id: String,
    val Title: String,
    val Year: String,
    val Type: String,
    val Poster: String?
)