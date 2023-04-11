package mad.nina_gallmetzer_mad.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mad.nina_gallmetzer_mad.screens.DetailScreen
import mad.nina_gallmetzer_mad.screens.FavoriteScreen
import mad.nina_gallmetzer_mad.screens.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable(
            route = "home"
        ) {
            HomeScreen(navController)
        }

        composable(
            route = "detail/{movieId}"
        ) { backStackEntry ->
            DetailScreen(navController, movieId = backStackEntry.arguments?.getString("movieId"))
        }

        composable(
            route = "favorites"
        ) {
            FavoriteScreen(navController)
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