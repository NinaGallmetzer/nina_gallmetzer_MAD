package mad.nina_gallmetzer_mad.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Movie::class, Image::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun imageDao(): ImageDao

    companion object {
        @Volatile
        private var Instance : MovieDatabase ? = null

        fun getDatabase (context: Context): MovieDatabase {
            return Instance ?: synchronized(this) {

                Room.databaseBuilder(context, MovieDatabase::class.java, "movie_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Thread(Runnable { prepopulateDb(context, getDatabase(context)) }).start()
                        }
                    })
                    .build()
                    .also {
                        Instance = it
                    }
            }
        }

        private fun prepopulateDb(context: Context, db: MovieDatabase) {
            for (movie in getInitialMovies()) {
                db.movieDao().insertOnCreate(movie)
            }

            for (image in getInitialImages()) {
                db.imageDao().insertOnCreate(image)
            }
        }
    }
}