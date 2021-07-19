package com.example.weatherdemo.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.weatherdemo.data.City
import com.example.weatherdemo.data.CurrentWeather
import com.example.weatherdemo.data.DailyWeather
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [City::class, CurrentWeather::class, DailyWeather::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherDemoDatabase : RoomDatabase() {

    abstract fun citiesDao(): CitiesDao
    abstract fun weatherDao(): WeatherDao

    companion object {
        @Volatile
        private var INSTANCE: WeatherDemoDatabase? = null

        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(WeatherDemoDatabase::class.java) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context): WeatherDemoDatabase = Room.databaseBuilder(
            context.applicationContext,
            WeatherDemoDatabase::class.java,
            "WeatherDemo.db"
        ).addCallback(object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                CoroutineScope(Dispatchers.IO).launch {
                    getInstance(context).citiesDao().insertCities(PREPOPULATE_CITIES)
                }
            }
        }).build()

        private val PREPOPULATE_CITIES = listOf(
            City(
                1261481,
                28.61282,
                77.23114,
                "New Delhi",
                "IN",
                10800
            ),
            City(
                1275339,
                19.01441,
                72.847939,
                "Mumbai",
                "IN",
                10800
            )
        )
    }
}