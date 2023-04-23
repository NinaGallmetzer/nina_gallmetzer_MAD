package mad.nina_gallmetzer_mad.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import mad.nina_gallmetzer_mad.data.Movie
import mad.nina_gallmetzer_mad.data.MovieRepository

class DetailScreenViewModel(
    private val movieRepository: MovieRepository,
    private val movieId: String
): ViewModel() {

    fun getMovieById(): Flow<Movie?> = movieRepository.getMovieById(movieId)

    private val _movieList = MutableStateFlow(listOf<Movie>())
    val movieList: StateFlow<List<Movie>> = _movieList.asStateFlow()

    init {
        viewModelScope.launch {
            movieRepository.getMovieById(movieId).collect() {
                _movieList.value = movieList.value
            }
        }
    }
}