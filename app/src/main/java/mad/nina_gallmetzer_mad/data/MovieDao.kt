package mad.nina_gallmetzer_mad.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: Movie)

    @Insert
    fun insertOnCreate(movie: Movie)

    @Update
    suspend fun update(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)

    @Query("SELECT * from Movie ORDER BY title ASC")
    fun getAllMovies(): Flow<List<Movie>>

    @Query("SELECT * from Movie WHERE isFavorite")
    fun getFavoriteMovies(): Flow<List<Movie>>

    @Query("SELECT * from Movie WHERE id = :id")
    fun getMovieById(id: String): Flow<Movie>

    @Transaction
    @Query("SELECT * FROM Movie")
    fun getMovieWithImages(): Flow<List<MovieWithImages>>

}