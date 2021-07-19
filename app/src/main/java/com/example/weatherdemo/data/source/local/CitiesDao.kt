package com.example.weatherdemo.data.source.local

import androidx.room.*
import com.example.weatherdemo.data.City
import com.example.weatherdemo.data.CityAndCurrentWeather
import kotlinx.coroutines.flow.Flow

@Dao
interface CitiesDao {

    @Query("select * from cities where id = :cityId")
    suspend fun getCity(cityId: Long): City

    @Query("select id from cities")
    suspend fun getCitiesIds(): List<Long>

    @Transaction
    @Query("select * from cities order by name")
    fun getCitiesAndCurrentWeather(): Flow<List<CityAndCurrentWeather>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCity(city: City)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCities(cities: List<City>)
}