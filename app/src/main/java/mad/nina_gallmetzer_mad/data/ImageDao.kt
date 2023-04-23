package mad.nina_gallmetzer_mad.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(image: Image)

    @Insert
    fun insertOnCreate(image: Image)

    @Query("SELECT * FROM Image")
    fun getAllImages(): Flow<List<Image>>

    @Query("SELECT imageUrl FROM Image WHERE movieId = :movieId")
    fun getImageUrlsByMovieId(movieId: String): Flow<List<String>>

}