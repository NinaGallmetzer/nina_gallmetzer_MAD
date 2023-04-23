package mad.nina_gallmetzer_mad.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import mad.nina_gallmetzer_mad.data.Movie
import mad.nina_gallmetzer_mad.data.MovieRepository

class HomeScreenViewModel(
    private val movieRepository: MovieRepository
    ): ViewModel() {

    private val _movieList = MutableStateFlow(listOf<Movie>())
    val movieList: StateFlow<List<Movie>> = _movieList.asStateFlow()

    init {
        viewModelScope.launch {
            movieRepository.getAllMovies().collect() { movieList ->
                if (movieList.isNotEmpty()) {
                    _movieList.value = movieList
                }
            }
        }
    }

    fun getAllMovies(): Flow<List<Movie>> {
        return movieRepository.getAllMovies()
    }

    suspend fun toggleFavoriteState(movie: Movie) {
        movie.isFavorite = !movie.isFavorite
        movieRepository.updateMovie(movie)
    }
}