package mad.nina_gallmetzer_mad.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import mad.nina_gallmetzer_mad.ui.navigation.HomeAppBar
import mad.nina_gallmetzer_mad.ui.MovieViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    movieViewModel: MovieViewModel = viewModel(),
    navController: NavController
) {
    Scaffold(
        topBar = {
            HomeAppBar(navController = navController)
        }
    ) {
        MovieList(movieViewModel = movieViewModel, navController = navController)
    }
}

@Composable
fun MovieList(
    movieViewModel: MovieViewModel,
    navController: NavController = rememberNavController()
) {
    LazyColumn {
        items (movieViewModel.movieList) { movie ->
            MovieRow(movie = movie, { movieViewModel.updateFavorites(movie.id) }) { movieId ->
                navController.navigate("detail/$movieId")
            }
        }
    }
}

