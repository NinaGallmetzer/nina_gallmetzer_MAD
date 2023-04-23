package mad.nina_gallmetzer_mad.ui.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import mad.nina_gallmetzer_mad.ui.screens.DetailScreen
import mad.nina_gallmetzer_mad.ui.screens.FavoriteScreen
import mad.nina_gallmetzer_mad.ui.screens.HomeScreen
import mad.nina_gallmetzer_mad.ui.MovieViewModel
import mad.nina_gallmetzer_mad.ui.screens.AddMovieScreen

@Composable
fun Navigation(
    movieViewModel: MovieViewModel,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            HomeScreen(navController)
        }

        composable(route = "detail/{movieId}") { backStackEntry ->
            DetailScreen(navController, movieId = backStackEntry.arguments?.getString("movieId"))
        }

        composable(route = "favorite") {
            FavoriteScreen(navController)
        }

        composable(route = "addMovie") {
            AddMovieScreen(Modifier, movieViewModel, navController)
        }

    }
}

@Composable
fun HomeAppBar(navController: NavController) {
    TopAppBar {
        var expanded by remember { mutableStateOf(false) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Movies",
                style = MaterialTheme.typography.h6)

            IconButton(
                modifier = Modifier.size(24.dp),
                onClick = { expanded = !expanded },
            ) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "DropdownMenu",
                )
            }
        }

        Row {
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                DropdownMenuItem(
                    onClick = {
                        navController.navigate("favorite")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Show Favorites",
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Favorites")
                }
                DropdownMenuItem(
                    onClick = {
                        navController.navigate("addMovie")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Add Movie",
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Add Movie")
                }
            }
        }
    }
}

