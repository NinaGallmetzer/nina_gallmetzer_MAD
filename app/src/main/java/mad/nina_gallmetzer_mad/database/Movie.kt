package mad.nina_gallmetzer_mad.database

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey
    val id: String,
    val title: String,
    val year: String,
    val genre: String,
    val director: String,
    val actors: String,
    val plot: String,
    val images: String,
    val rating: Float,
    var isFavorite: Boolean = false
)