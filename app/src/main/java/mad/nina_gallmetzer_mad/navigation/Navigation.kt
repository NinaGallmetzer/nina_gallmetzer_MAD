package mad.nina_gallmetzer_mad.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mad.nina_gallmetzer_mad.screens.DetailScreen
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
    }
}