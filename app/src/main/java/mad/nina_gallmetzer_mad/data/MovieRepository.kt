package mad.nina_gallmetzer_mad.data

import kotlinx.coroutines.flow.Flow

class MovieRepository (private val movieDao: MovieDao, private val imageDao: ImageDao) {

    suspend fun addMovie(movie: Movie) = movieDao.insert(movie = movie)

    suspend fun updateMovie(movie: Movie) = movieDao.update(movie = movie)

    fun getAllMovies(): Flow<List<Movie>> = movieDao.getAllMovies()

    fun getFavoriteMovies(): Flow<List<Movie>> = movieDao.getFavoriteMovies()

    fun getMovieById(id: String): Flow<Movie> = movieDao.getMovieById(id)

    fun getAllImages(): Flow<List<Image>> = imageDao.getAllImages()

    fun getImageUrlsByMovieId(movieId: String): Flow<List<String>> = imageDao.getImageUrlsByMovieId(movieId = movieId)

}
