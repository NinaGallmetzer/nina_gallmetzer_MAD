package mad.nina_gallmetzer_mad.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies
import mad.nina_gallmetzer_mad.navigation.SimpleAppBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FavoriteScreen(navController: NavController) {
    Scaffold(
        topBar = {
            SimpleAppBar(navController = navController, title = "Favorite Movies")
        }
    ) {
        Text(text = "Screen displaying favorite movies")
        FavoriteMovieList(navController = navController)
    }
}

@Composable
fun FavoriteMovieList(
    movies: List<Movie> = getMovies().take(3),
    navController: NavController = rememberNavController()
) {
    LazyColumn {
        items (movies) { movie ->
            MovieRow(movie = movie) { movieId ->
                navController.navigate("detail/$movieId")
            }
        }
    }
}
