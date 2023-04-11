package mad.nina_gallmetzer_mad.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies

class MovieViewModel: ViewModel() {

    private val _movieList = getMovies().toMutableStateList()
    val movieList: List<Movie>
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

}