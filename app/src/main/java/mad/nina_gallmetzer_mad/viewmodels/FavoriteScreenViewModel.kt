package mad.nina_gallmetzer_mad.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import mad.nina_gallmetzer_mad.data.Image
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
                _movieList.value = movieList
            }
        }
    }

    private val _imageList = MutableStateFlow(listOf<Image>())
    val imageList: StateFlow<List<Image>> = _imageList.asStateFlow()

    init {
        viewModelScope.launch {
            movieRepository.getAllImages().collect() { imageList ->
                _imageList.value = imageList
            }
        }
    }

    suspend fun updateMovie(movie: Movie) {
        movie.isFavorite = !movie.isFavorite
        movieRepository.updateMovie(movie)
    }
}