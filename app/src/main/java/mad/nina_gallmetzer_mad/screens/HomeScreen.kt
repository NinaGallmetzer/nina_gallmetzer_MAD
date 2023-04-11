package mad.nina_gallmetzer_mad.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.movieapp.models.Movie
import mad.nina_gallmetzer_mad.navigation.HomeAppBar
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

@Composable
fun MovieRow(
    movie: Movie,
    onFavoriteClick: (String) -> Unit = {},
    onItemClick: (String) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    var favorite by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { onItemClick(movie.id) },
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        elevation = 5.dp
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    model = movie.images[0],
                    contentDescription = movie.title,
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    contentAlignment = Alignment.TopEnd
                ) {
                    Icon(
                        tint = MaterialTheme.colors.secondary,
                        imageVector = if(favorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = "Add to favorites",
                        modifier = Modifier
                            .clickable {
                                favorite = !favorite
                                onFavoriteClick(movie.id)
                            }
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .padding(5.dp))
                IconButton(
                    modifier = Modifier.size(24.dp),
                    onClick = { expanded = !expanded },
                ) {
                    if (expanded) {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowUp,
                            contentDescription = ("Hide details")
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowDown,
                            contentDescription = ("Show details")
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                if (expanded) {
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        Text(text = "Director: ${movie.director}")
                        Text(text = "Released: ${movie.year}")
                        Text(text = "Genre: ${movie.genre}")
                        Text(text = "Actors: ${movie.actors}")
                        Text(text = "Rating: ${movie.rating}")
                        Divider(
                            modifier = Modifier
                                .padding(5.dp))
                        Text(
                            text = "Plot: ${movie.plot}",
                            modifier = Modifier
                                .padding(5.dp))
                    }
                }
            }
        }
    }
}

