package mad.nina_gallmetzer_mad.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import mad.nina_gallmetzer_mad.data.Movie
import mad.nina_gallmetzer_mad.data.MovieRepository

class FavoriteScreenViewModel(
    private val movieRepository: MovieRepository
    ): ViewModel() {

    private val _movieList = MutableStateFlow(listOf<Movie>())
    val movieList: StateFlow<List<Movie>> = _movieList.asStateFlow()

    init {
        viewModelScope.launch {
            movieRepository.getFavoriteMovies().collect() { movieList ->
                if (movieList.isNotEmpty()) {
                    _movieList.value = movieList
                }
            }
        }
    }

    fun getAllFavorites(): Flow<List<Movie>> {
        return movieRepository.getFavoriteMovies()
    }

    suspend fun toggleFavoriteState(movie: Movie) {
        movie.isFavorite = !movie.isFavorite
        movieRepository.updateMovie(movie)
    }

}
