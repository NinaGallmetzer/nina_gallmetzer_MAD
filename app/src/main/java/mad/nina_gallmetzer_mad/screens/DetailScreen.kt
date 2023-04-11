package mad.nina_gallmetzer_mad.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import mad.nina_gallmetzer_mad.navigation.SimpleAppBar
import mad.nina_gallmetzer_mad.ui.MovieViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(
    movieViewModel: MovieViewModel,
    navController: NavController,
    movieId: String?
) {
    movieId?.let {
        val movie = movieViewModel.movieList.find { it.id == movieId
        }
        movie?.let {
            Scaffold(
                topBar = {
                    SimpleAppBar(navController = navController, title = movie.title)
                }
            ) {
                Column {
                    MovieRow(movie = movie, { movieViewModel.updateFavorites(movie.id) })

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
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(300.dp)
                                    .padding(5.dp)
                            )
                        }
                    }
                }
            }
        }
    }

}