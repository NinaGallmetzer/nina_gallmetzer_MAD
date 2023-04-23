package mad.nina_gallmetzer_mad.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mad.nina_gallmetzer_mad.data.Movie
import mad.nina_gallmetzer_mad.data.MovieRepository

class DetailScreenViewModel(
    private val movieRepository: MovieRepository,
    movieId: String = ""
): ViewModel() {

    private val _movie = MutableStateFlow(Movie())
    val movie: StateFlow<Movie> = _movie.asStateFlow()

    init {
        viewModelScope.launch {
            movieRepository.getMovieById(movieId).collect() { movie ->
                _movie.value = movie
            }
        }
    }

    private val _imageUrlList = MutableStateFlow(listOf<String>())
    val imageUrlList: StateFlow<List<String>> = _imageUrlList.asStateFlow()

    init {
        viewModelScope.launch() {
            movieRepository.getImageUrlsByMovieId(movieId).collect() { imageUrl ->
                _imageUrlList.value = imageUrl
            }
        }
    }

    suspend fun toggleFavoriteState(movie: Movie) {
        movie.isFavorite = !movie.isFavorite
        movieRepository.updateMovie(movie)
    }



}