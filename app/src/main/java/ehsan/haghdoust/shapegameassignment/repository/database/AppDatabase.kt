package ehsan.haghdoust.shapegameassignment.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ehsan.haghdoust.shapegameassignment.repository.database.dao.DaoAccess
import ehsan.haghdoust.shapegameassignment.repository.database.entity.LikedImage

@Database(entities = [LikedImage::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract val dao: DaoAccess

    companion object {

        //  Volatile means that writings to this field are immediately made visible to other threa
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "AppDatabase").build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
