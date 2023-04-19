package mad.nina_gallmetzer_mad.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import mad.nina_gallmetzer_mad.database.Movie
import mad.nina_gallmetzer_mad.database.MovieRepository

class DetailScreenViewModel(
    private val movieId: String,
    private val movieRepository: MovieRepository
): ViewModel() {

    fun getMovieById(): Flow<Movie?> = movieRepository.getMovieById(movieId)

}