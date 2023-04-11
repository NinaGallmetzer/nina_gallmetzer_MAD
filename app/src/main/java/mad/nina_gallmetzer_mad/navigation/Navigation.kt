package mad.nina_gallmetzer_mad.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import mad.nina_gallmetzer_mad.screens.DetailScreen
import mad.nina_gallmetzer_mad.screens.FavoriteScreen
import mad.nina_gallmetzer_mad.screens.HomeScreen
import mad.nina_gallmetzer_mad.ui.MovieViewModel

@Composable
fun Navigation(
    movieViewModel: MovieViewModel,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            HomeScreen(movieViewModel, navController)
        }

        composable(route = "detail/{movieId}") { backStackEntry ->
            DetailScreen(movieViewModel, navController, movieId = backStackEntry.arguments?.getString("movieId"))
        }

        composable(route = "favorites") {
            FavoriteScreen(movieViewModel, navController)
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
                        navController.navigate("favorites")
                    }
                ) {
                    Row {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = "DropdownMenu",
                        )
                        Text(text = "  Favorites")
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