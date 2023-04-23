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
    val db = MovieDatabase.getDatabase(LocalContext.current)
    val repository = MovieRepository(movieDao = db.movieDao(), imageDao = db.imageDao())
    val factory = HomeScreenViewModelFactory(repository = repository)
    val homeScreenViewModel: HomeScreenViewModel = viewModel(factory = factory)

    Scaffold(
        topBar = {
            HomeAppBar(navController = navController)
        }
    ) {
        MovieList(homeScreenViewModel = homeScreenViewModel, navController = navController)
    }
}

@Composable
fun MovieList(
    homeScreenViewModel: HomeScreenViewModel,
    navController: NavController = rememberNavController()
) {
    Text(text = "test on Home Screen")
    LazyColumn {
        items (homeScreenViewModel.movieList.value) { movie ->
            MovieRow(movie = movie)
        }
    }
}

