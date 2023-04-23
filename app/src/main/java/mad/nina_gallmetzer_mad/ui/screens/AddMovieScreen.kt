package mad.nina_gallmetzer_mad.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import mad.nina_gallmetzer_mad.R
import mad.nina_gallmetzer_mad.data.Genre
import mad.nina_gallmetzer_mad.data.MovieDatabase
import mad.nina_gallmetzer_mad.data.MovieRepository
import mad.nina_gallmetzer_mad.viewmodels.AddMovieScreenViewModel
import mad.nina_gallmetzer_mad.viewmodels.AddMovieScreenViewModelFactory

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddMovieScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val db = MovieDatabase.getDatabase(LocalContext.current)
    val repository = MovieRepository(movieDao = db.movieDao(), imageDao = db.imageDao())
    val factory = AddMovieScreenViewModelFactory(repository = repository)
    val addMovieScreenViewModel: AddMovieScreenViewModel = viewModel(factory = factory)

    val coroutineScope = rememberCoroutineScope()

    Column {
        SimpleAppBar(title = "Add a Movie", navController = navController)
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {

                var title by remember {
                    mutableStateOf("")
                }

                var isTitleEmpty by remember {
                    mutableStateOf(false)
                }

                var year by remember {
                    mutableStateOf("")
                }
                var isYearEmpty by remember {
                    mutableStateOf(false)
                }

                val genres = Genre.values().toList()

                var genreItems by remember {
                    mutableStateOf(
                        genres.map { genre ->
                            AddMovieScreenViewModel.ListItemSelectable(
                                title = genre,
                                isSelected = false
                            )
                        }
                    )
                }

                var director by remember {
                    mutableStateOf("")
                }
                var isDirectorEmpty by remember {
                    mutableStateOf(false)
                }

                var actors by remember {
                    mutableStateOf("")
                }
                var isActorsEmpty by remember {
                    mutableStateOf(false)
                }

                var plot by remember {
                    mutableStateOf("")
                }

                var rating by remember {
                    mutableStateOf("")
                }
                var isRatingEmpty by remember {
                    mutableStateOf(false)
                }

                var isEnabledSaveButton by remember {
                    mutableStateOf(false)
                }

                OutlinedTextField(
                    value = title,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {
                        title = it
                        isTitleEmpty = it.isEmpty()
                    },
                    label = {
                        Text(text = stringResource(R.string.enter_movie_title))
                    },
                    isError = isTitleEmpty,
                )
                if(isTitleEmpty) {
                    Text(
                        text = stringResource(R.string.enter_movie_title_required),
                        color = Color.Red
                    )
                }

                OutlinedTextField(
                    value = year,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {
                        year = it
                        isYearEmpty = it.isEmpty()
                    },
                    label = { Text(stringResource(R.string.enter_movie_year)) },
                    isError = isYearEmpty
                )
                if(isYearEmpty) {
                    Text(
                        text = stringResource(R.string.enter_movie_year_required),
                        color = Color.Red
                    )
                }

                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = stringResource(R.string.select_genres),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h6
                )

                LazyHorizontalGrid(
                    modifier = Modifier.height(100.dp),
                    rows = GridCells.Fixed(3)){
                    items(genreItems.size) { index ->
                        val genreItem = genreItems[index]
                        Chip(
                            modifier = Modifier.padding(2.dp),
                            colors = ChipDefaults.chipColors(
                                backgroundColor = if (genreItem.isSelected)
                                    colorResource(id = R.color.purple_200)
                                else
                                    colorResource(id = R.color.white)
                            ),
                            onClick = {
                                genreItems = genreItems.map {
                                    if (it.title == genreItem.title) {
                                        genreItem.copy(isSelected = !genreItem.isSelected)
                                    } else {
                                        it
                                    }
                                }
                            }
                        ) {
                            Text(text = genreItem.title.name)
                        }
                    }
                }

                OutlinedTextField(
                    value = director,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {
                        director = it
                        isDirectorEmpty = it.isEmpty()
                    },
                    label = { Text(stringResource(R.string.enter_director)) },
                    isError = isDirectorEmpty,
                )
                if(isDirectorEmpty) {
                    Text(
                        text = stringResource(R.string.enter_movie_director_required),
                        color = Color.Red
                    )
                }

                OutlinedTextField(
                    value = actors,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {
                        actors = it
                        isActorsEmpty = it.isEmpty()
                    },
                    label = { Text(stringResource(R.string.enter_actors)) },
                    isError = isActorsEmpty
                )
                if(isActorsEmpty) {
                    Text(
                        text = stringResource(R.string.enter_movie_actors_required),
                        color = Color.Red
                    )
                }

                OutlinedTextField(
                    value = plot,
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    onValueChange = { plot = it },
                    label = {
                        Text(
                            textAlign = TextAlign.Start,
                            text = stringResource(R.string.enter_plot)
                        )
                    },
                    isError = false
                )

                OutlinedTextField(
                    value = rating,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {
                        rating = if (it.startsWith("0")) {
                            ""
                        } else {
                            it
                        }
                        isRatingEmpty = it.isEmpty()
                    },
                    label = { Text(stringResource(R.string.enter_rating)) },
                    isError = isRatingEmpty
                )
                if(isRatingEmpty) {
                    Text(
                        text = stringResource(R.string.enter_movie_rating_required),
                        color = Color.Red
                    )
                }

                isEnabledSaveButton = addMovieScreenViewModel.isValidMovie(title, year, genreItems.filter { x -> x.isSelected }.map { x -> x.title }, director, actors, rating.toFloatOrNull() ?: 0.0f)

                Button(
                    enabled = isEnabledSaveButton,
                    onClick = {
                        coroutineScope.launch {
                            addMovieScreenViewModel.addNewMovie(title, year, genreItems.filter { x -> x.isSelected }.map { x -> x.title }, director, actors, plot, "https://upload.wikimedia.org/wikipedia/commons/1/14/No_Image_Available.jpg", rating.toFloatOrNull() ?: 0.0f)
                        }
                        navController.navigate("home")
                    }) {
                    Text(text = stringResource(R.string.add))
                }
            }
        }
    }
}
