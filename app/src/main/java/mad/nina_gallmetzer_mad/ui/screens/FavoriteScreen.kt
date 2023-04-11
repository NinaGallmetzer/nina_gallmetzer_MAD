package mad.nina_gallmetzer_mad.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import mad.nina_gallmetzer_mad.ui.MovieViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FavoriteScreen(
    movieViewModel: MovieViewModel,
    navController: NavController
) {
    Scaffold(
        topBar = {
            SimpleAppBar(navController = navController, title = "My Favorite Movies")
        }
    ) {
        LazyColumn {
            items (movieViewModel.favoriteMovieList) { movie ->
                MovieRow(movie = movie, { movieViewModel.updateFavorites(movie.id) }) { movieId ->
                    navController.navigate("detail/$movieId")
                }
            }
        }
    }
}
