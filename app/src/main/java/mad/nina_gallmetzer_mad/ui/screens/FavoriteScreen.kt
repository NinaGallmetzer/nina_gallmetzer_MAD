package mad.nina_gallmetzer_mad.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import mad.nina_gallmetzer_mad.data.MovieDatabase
import mad.nina_gallmetzer_mad.data.MovieRepository
import mad.nina_gallmetzer_mad.viewmodels.FavoriteScreenViewModel
import mad.nina_gallmetzer_mad.viewmodels.FavoriteScreenViewModelFactory

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FavoriteScreen(
    navController: NavController
) {
    val db = MovieDatabase.getDatabase(LocalContext.current)
    val repository = MovieRepository(movieDao = db.movieDao(), imageDao = db.imageDao())
    val factory = FavoriteScreenViewModelFactory(repository = repository)
    val favoriteScreenViewModel: FavoriteScreenViewModel = viewModel(factory = factory)

    Scaffold(
        topBar = {
            SimpleAppBar(navController = navController, title = "My Favorite Movies")
        }
    ) {
        LazyColumn {
            items (favoriteScreenViewModel.movieList.value) { movie ->
                MovieRow(movie = movie)
            }
        }
    }
}
