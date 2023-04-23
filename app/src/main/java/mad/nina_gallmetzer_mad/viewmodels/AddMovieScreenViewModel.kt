package mad.nina_gallmetzer_mad.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import mad.nina_gallmetzer_mad.data.Movie
import mad.nina_gallmetzer_mad.data.MovieRepository
import mad.nina_gallmetzer_mad.data.Genre

class AddMovieScreenViewModel(
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

    data class ListItemSelectable(
        val title: Genre,
        val isSelected: Boolean
    )

    fun isValidMovie(title: String, year: String, genres: List<Genre>, director: String, actors: String, rating: Float): Boolean {
        return (title.isNotBlank() && year.isNotBlank() && genres.isNotEmpty() && director.isNotBlank() && actors.isNotBlank() && rating > 0.0f)
    }

    suspend fun addNewMovie(title: String, year: String, genres: List<Genre>, director: String, actors: String, plot: String, titleImage: String, rating: Float) {
        if(isValidMovie(title, year, genres, director, actors, rating)) {
            val lastId = movieList.last().last().id.substring(2).toIntOrNull() ?: 0
            val newMovie = Movie(
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
            movieRepository.addMovie(newMovie)
        }
    }
}