package mad.nina_gallmetzer_mad.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import mad.nina_gallmetzer_mad.models.Movie
import mad.nina_gallmetzer_mad.models.getMovies
import mad.nina_gallmetzer_mad.models.Genre

class MovieViewModel: ViewModel() {

    data class ListItemSelectable(
        val title: Genre,
        val isSelected: Boolean
    )

    private val _movieList = getMovies().toMutableStateList()
    val movieList: MutableList<Movie>
        get() = _movieList

    private val _favoriteMovieList: MutableList<Movie> = mutableStateListOf()
    val favoriteMovieList: List<Movie>
        get() = _favoriteMovieList

    fun updateFavorites(movieID: String) {
        val movie = movieList.find { it.id == movieID}
        movie?.let {
            val favoriteState: Boolean = movie.isFavorite
            movie.isFavorite = !favoriteState

            if(!favoriteState) {
                _favoriteMovieList.add(movie)
            } else{
                _favoriteMovieList.remove(movie)
            }
        }
    }

    private fun resetFavoriteMovieList() {
        _favoriteMovieList.clear()
    }

    init {
        resetFavoriteMovieList()
    }

    fun isValidMovie(title: String, year: String, genres: List<Genre>, director: String, actors: String, rating: Float): Boolean {
        return (title.isNotBlank() && year.isNotBlank() && genres.isNotEmpty() && director.isNotBlank() && actors.isNotBlank() && rating > 0.0f)
    }

    fun addNewMovie(title: String, year: String, genres: List<Genre>, director: String, actors: String, plot: String, images: List<String>, rating: Float) {
        if(isValidMovie(title, year, genres, director, actors, rating)) {
            val lastId = movieList.last().id.substring(2).toIntOrNull() ?: 0
            movieList.add(
                Movie(
                    id = "tt${lastId + 1}",
                    title = title,
                    year = year,
                    genre = genres.toString(),
                    director = director,
                    actors = actors,
                    plot = plot,
                    images = images,
                    rating = rating
                )
            )
        }
    }
}