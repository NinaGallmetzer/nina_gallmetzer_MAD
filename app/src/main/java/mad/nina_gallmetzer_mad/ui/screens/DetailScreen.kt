package mad.nina_gallmetzer_mad.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import mad.nina_gallmetzer_mad.data.MovieDatabase
import mad.nina_gallmetzer_mad.data.MovieRepository
import mad.nina_gallmetzer_mad.viewmodels.DetailScreenViewModel
import mad.nina_gallmetzer_mad.viewmodels.DetailScreenViewModelFactory

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun DetailScreen(
    navController: NavController = rememberNavController(),
    movieId: String?
) {
    val db = MovieDatabase.getDatabase(LocalContext.current)
    val repository = MovieRepository(movieDao = db.movieDao(), imageDao = db.imageDao())
    val factory = movieId?.let { DetailScreenViewModelFactory(repository = repository, movieId = it) }
    val detailScreenViewModel: DetailScreenViewModel = viewModel(factory = factory)

    val movie = detailScreenViewModel.movie.value
    val imageUrlList = detailScreenViewModel.imageUrlList
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            SimpleAppBar(navController = navController, title = movie.title)
        }
    ) {
        Column {
            MovieRow(
                movie = movie,
                onFavoriteClick = {
                    coroutineScope.launch {
                        detailScreenViewModel.toggleFavoriteState(movie)
                    }
                },
            )

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
                items(imageUrlList.value) { image ->
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