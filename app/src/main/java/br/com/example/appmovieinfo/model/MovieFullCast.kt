package br.com.example.appmovieinfo.model

data class MovieFullCast(
    val actorList: List<Actor>,
    val awards: String,
    val countries: String,
    val directors: String,
    val fullCast: FullCast,
    val fullTitle: String,
    val genreList: List<Genre>,
    val genres: String,
    val id: String,
    val imDbRating: String,
    val imDbRatingVotes: String,
    val image: String,
    val images: Images,
    val languages: String,
    val originalTitle: String,
    val plot: String,
    val plotLocal: String,
    val plotLocalIsRtl: Boolean,
    val posters: Posters,
    val ratings: Ratings,
    val releaseDate: String,
    val runtimeMins: String,
    val runtimeStr: String,
    val similars: List<Similar>,
    val starList: List<Star>,
    val stars: String,
    val tagline: String,
    val title: String,
    val trailer: Trailer,
    val tvEpisodeInfo: String?,
    val tvSeriesInfo: String?,
    val type: String,
    val wikipedia: Wikipedia,
    val writerList: List<Writer>,
    val writers: String,
    val year: String
)

data class Actor(
    val asCharacter: String,
    val id: String,
    val image: String,
    val name: String
)


data class Company(
    val id: String,
    val name: String
)

data class Country(
    val key: String,
    val value: String
)

data class Director(
    val id: String,
    val name: String
)

data class FullCast(
    val actors: List<ActorX>,
    val directors: Directors,
    val errorMessage: String?,
    val fullTitle: String,
    val imDbId: String,
    val others: List<Other>,
    val title: String,
    val type: String,
    val writers: Writers,
    val year: String
)

data class Genre(
    val key: String,
    val value: String
)

data class Images(
    val errorMessage: String,
    val fullTitle: String,
    val imDbId: String,
    val items: List<ItemXXX>,
    val title: String,
    val type: String,
    val year: String
)

data class Language(
    val key: String,
    val value: String
)

data class Posters(
    val backdrops: List<Backdrop>,
    val errorMessage: String,
    val fullTitle: String,
    val imDbId: String,
    val posters: List<Poster>,
    val title: String,
    val type: String,
    val year: String
)

data class Ratings(
    val errorMessage: String,
    val filmAffinity: String,
    val fullTitle: String,
    val imDb: String,
    val imDbId: String,
    val metacritic: String,
    val rottenTomatoes: String,
    val theMovieDb: String,
    val title: String,
    val type: String,
    val year: String
)

data class Similar(
    val id: String,
    val imDbRating: String,
    val image: String,
    val title: String
)

data class Star(
    val id: String,
    val name: String
)

data class Trailer(
    val errorMessage: String,
    val fullTitle: String,
    val imDbId: String,
    val link: String,
    val linkEmbed: String,
    val thumbnailUrl: String,
    val title: String,
    val type: String,
    val uploadDate: String?,
    val videoDescription: String,
    val videoId: String,
    val videoTitle: String,
    val year: String
)

data class Wikipedia(
    val errorMessage: String,
    val fullTitle: String,
    val imDbId: String,
    val language: String,
    val plotFull: PlotFull,
    val plotShort: PlotShort,
    val title: String,
    val titleInLanguage: String,
    val type: String,
    val url: String,
    val year: String
)

data class Writer(
    val id: String,
    val name: String
)

data class ActorX(
    val asCharacter: String,
    val id: String,
    val image: String,
    val name: String
)

data class Directors(
    val items: List<Item>,
    val job: String
)

data class Other(
    val items: List<ItemX>,
    val job: String
)

data class Writers(
    val items: List<ItemXX>,
    val job: String
)

data class Item(
    val description: String,
    val id: String,
    val name: String
)

data class ItemX(
    val description: String,
    val id: String,
    val name: String
)

data class ItemXX(
    val description: String,
    val id: String,
    val name: String
)

data class ItemXXX(
    val image: String,
    val title: String
)

data class Backdrop(
    val aspectRatio: Double,
    val height: Int,
    val id: String,
    val language: String,
    val link: String,
    val width: Int
)

data class Poster(
    val aspectRatio: Double,
    val height: Int,
    val id: String,
    val language: String,
    val link: String,
    val width: Int
)

data class PlotFull(
    val html: String,
    val plainText: String
)

data class PlotShort(
    val html: String,
    val plainText: String
)
