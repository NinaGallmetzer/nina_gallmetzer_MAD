package mad.nina_gallmetzer_mad.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import mad.nina_gallmetzer_mad.data.MovieDatabase
import mad.nina_gallmetzer_mad.data.MovieRepository
import mad.nina_gallmetzer_mad.viewmodels.DetailScreenViewModel
import mad.nina_gallmetzer_mad.viewmodels.DetailScreenViewModelFactory

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(
    navController: NavController,
    movieId: String?
) {
    val db = MovieDatabase.getDatabase(LocalContext.current)
    val repository = MovieRepository(movieDao = db.movieDao(), imageDao = db.imageDao())
    val factory = movieId?.let { DetailScreenViewModelFactory(repository = repository, movieId = it) }
    val detailScreenViewModel: DetailScreenViewModel = viewModel(factory = factory)

/*
    movieId.let {
        val movie = detailScreenViewModel.getMovieById()
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
*/

}