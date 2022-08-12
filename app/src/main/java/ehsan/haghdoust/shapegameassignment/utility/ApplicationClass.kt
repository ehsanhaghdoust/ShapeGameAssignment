package ehsan.haghdoust.shapegameassignment.utility

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import ehsan.haghdoust.shapegameassignment.repository.database.AppDatabase

class ApplicationClass: Application() {

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()

        sharedPreferences = applicationContext().getSharedPreferences("ehsan.haghdoust.shapegameassignment", MODE_PRIVATE)
        database = Room.databaseBuilder(this, AppDatabase::class.java, "AppDatabase").build()
    }

    companion object {

        var database: AppDatabase? = null
        private var instance: ApplicationClass? = null
        private lateinit var sharedPreferences: SharedPreferences
        fun applicationContext() : Context {
            return instance!!.applicationContext
        }

        fun getSharedPreferences(): SharedPreferences {
            return sharedPreferences
        }
    }

}