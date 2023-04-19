package mad.nina_gallmetzer_mad.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import mad.nina_gallmetzer_mad.database.Movie
import mad.nina_gallmetzer_mad.database.MovieRepository

class HomeScreenViewModel(
    private val movieRepository: MovieRepository
    ): ViewModel() {

    fun getAllMovies(): Flow<List<Movie>> = movieRepository.getAllMovies()

}