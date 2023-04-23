package mad.nina_gallmetzer_mad.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import mad.nina_gallmetzer_mad.data.MovieDatabase
import mad.nina_gallmetzer_mad.data.MovieRepository
import mad.nina_gallmetzer_mad.ui.navigation.HomeAppBar
import mad.nina_gallmetzer_mad.viewmodels.HomeScreenViewModel
import mad.nina_gallmetzer_mad.viewmodels.HomeScreenViewModelFactory

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            HomeAppBar(navController = navController)
        }
    ) {
        MovieList(navController = navController)
    }
}


@Composable
fun MovieList(
    navController: NavController = rememberNavController()
) {
    val db = MovieDatabase.getDatabase(LocalContext.current)
    val repository = MovieRepository(movieDao = db.movieDao(), imageDao = db.imageDao())
    val factory = HomeScreenViewModelFactory(repository = repository)
    val homeScreenViewModel: HomeScreenViewModel = viewModel(factory = factory)

    val movieList = homeScreenViewModel.movieList.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    LazyColumn {
        items (movieList.value) { movie ->
            MovieRow(
                movie = movie,
                onFavoriteClick = {
                    coroutineScope.launch {
                        homeScreenViewModel.toggleFavoriteState(movie)
                    }
                },
                onItemClick = { movieId ->
                    navController.navigate("detail/$movieId")
                }
            )
        }
    }
}