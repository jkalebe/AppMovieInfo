package br.com.example.appmovieinfo.repositoryimdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieImdbDB(
    @PrimaryKey
    val id:String,
    val title:String,
    val description:String,
    val image: String
)