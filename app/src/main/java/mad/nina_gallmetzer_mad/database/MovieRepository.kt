package mad.nina_gallmetzer_mad.database

import kotlinx.coroutines.flow.Flow

class MovieRepository (private val movieDao: MovieDao) {
    suspend fun addMovie(movie: Movie) = movieDao.insert(movie = movie)

    suspend fun updateMovie(movie: Movie) = movieDao.update(movie = movie)

    suspend fun deleteMove(movie: Movie) = movieDao.delete(movie = movie)

    fun getAllMovies(): Flow<List<Movie>> = movieDao.getAllMovies()

    fun getFavoriteMovies(): Flow<List<Movie>> = movieDao.getFavoriteMovies()

    fun getMovieById(id: String): Flow<Movie?> = movieDao.getMovieById(id)
}
