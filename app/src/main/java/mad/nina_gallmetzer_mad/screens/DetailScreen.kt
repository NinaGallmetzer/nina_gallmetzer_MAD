package mad.nina_gallmetzer_mad.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movieapp.models.getMovies
import mad.nina_gallmetzer_mad.navigation.SimpleAppBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(navController: NavController, movieId: String?) {
    val movie = getMovies().filter { it.id == movieId } [0]

    Scaffold(
        topBar = {
            SimpleAppBar(navController = navController, title = movie.title)
        }
    ) {
        Column {
            MovieRow(movie = movie)

            Divider(
                modifier = Modifier
                    .padding(10.dp)
            )
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Movie Images",
                    style = MaterialTheme.typography.h5
                )
            }

            LazyRow {

                items(movie.images) { image ->
                    AsyncImage(
                        model = image,
                        contentDescription = movie.title,
                        modifier = Modifier
                            .height(200.dp)
                            .padding(5.dp)
                    )
                }
            }
        }
    }
}