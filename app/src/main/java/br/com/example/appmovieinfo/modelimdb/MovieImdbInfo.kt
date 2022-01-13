package br.com.example.appmovieinfo.modelimdb

data class MovieImdbInfo(
    val id: String,
    val title: String,
    val type: String,
    val year: String,
    val image: String,
    val runtimeStr: String,
    val plot: String,
    val genreList: List<Genre>,
    val actorList: List<Actor>

)