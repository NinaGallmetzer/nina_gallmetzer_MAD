package mad.nina_gallmetzer_mad.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mad.nina_gallmetzer_mad.data.MovieRepository

class DetailScreenViewModelFactory (
    private val repository : MovieRepository,
    private val movieId : String
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailScreenViewModel::class.java)){
            return DetailScreenViewModel(movieRepository = repository, movieId = movieId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
