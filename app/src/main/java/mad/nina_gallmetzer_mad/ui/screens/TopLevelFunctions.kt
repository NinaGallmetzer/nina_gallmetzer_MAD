package mad.nina_gallmetzer_mad.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.navigation.NavController
import coil.compose.AsyncImage
import mad.nina_gallmetzer_mad.models.Movie

@Composable
fun MovieRow(
    movie: Movie,
    onFavoriteClick: (String) -> Unit = {},
    onItemClick: (String) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    var favorite by remember { mutableStateOf(movie.isFavorite) }

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

@Composable
fun SimpleAppBar(navController: NavController, title: String) {
    TopAppBar {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            IconButton(
                modifier = Modifier.size(24.dp),
                onClick = {
                    navController.navigate("home") {
                        popUpTo("home")
                    }
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "GoBack"
                )
            }

            Text(
                text = title,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(horizontal = 20.dp))
        }
    }
}