package mad.nina_gallmetzer_mad.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mad.nina_gallmetzer_mad.database.MovieRepository

class HomeScreenViewModelFactory (private val repository : MovieRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeScreenViewModel::class.java)){
            return HomeScreenViewModel(movieRepository = repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
