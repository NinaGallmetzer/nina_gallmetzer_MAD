package mad.nina_gallmetzer_mad.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import mad.nina_gallmetzer_mad.data.Image
import mad.nina_gallmetzer_mad.data.Movie
import mad.nina_gallmetzer_mad.data.MovieRepository
import mad.nina_gallmetzer_mad.data.Genre

class HomeScreenViewModel(
    private val movieRepository: MovieRepository
    ): ViewModel() {

    private val _movieList = MutableStateFlow(listOf<Movie>())
    val movieList: StateFlow<List<Movie>> = _movieList.asStateFlow()

    init {
        viewModelScope.launch {
            movieRepository.getAllMovies().collect() { movieList ->
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

    fun isValidMovie(title: String, year: String, genres: List<Genre>, director: String, actors: String, rating: Float): Boolean {
        return (title.isNotBlank() && year.isNotBlank() && genres.isNotEmpty() && director.isNotBlank() && actors.isNotBlank() && rating > 0.0f)
    }

    suspend fun addNewMovie(title: String, year: String, genres: List<Genre>, director: String, actors: String, plot: String, titleImage: String, rating: Float) {
        if(isValidMovie(title, year, genres, director, actors, rating)) {
            val lastId = movieList.last().last().id.substring(2).toIntOrNull() ?: 0
            movieRepository.addMovie(
                Movie(
                    id = "tt${lastId + 1}",
                    title = title,
                    year = year,
                    genre = genres.toString(),
                    director = director,
                    actors = actors,
                    plot = plot,
                    titleImage = titleImage,
                    rating = rating
                )
            )
        }
    }
}