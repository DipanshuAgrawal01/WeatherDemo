package com.example.weatherdemo.data.source.local

import com.example.weatherdemo.data.City
import com.example.weatherdemo.data.CityAndCurrentWeather
import kotlinx.coroutines.flow.Flow

interface CitiesLocalDataSource {

    suspend fun getCity(cityId: Long): City

    suspend fun getCitiesIds(): List<Long>

    fun getCitiesAndCurrentWeather(): Flow<List<CityAndCurrentWeather>>

    suspend fun saveCity(city: City)
}