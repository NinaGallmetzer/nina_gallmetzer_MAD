package mad.nina_gallmetzer_mad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import mad.nina_gallmetzer_mad.navigation.Navigation
import mad.nina_gallmetzer_mad.ui.MovieViewModel
import mad.nina_gallmetzer_mad.ui.theme.Nina_gallmetzer_MADTheme

class MainActivity : ComponentActivity() {
    lateinit var movieViewModel: MovieViewModel
    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Nina_gallmetzer_MADTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    movieViewModel = viewModel()
                    navController = rememberNavController()
                    Navigation(movieViewModel, navController)
                }
            }
        }
    }
}
