package com.example.weatherdemo.data.source

import com.example.weatherdemo.data.City
import com.example.weatherdemo.data.CityAndCurrentWeather
import kotlinx.coroutines.flow.Flow

interface CitiesRepository {

    suspend fun getCity(cityId: Long): City

    suspend fun getCitiesIds(): List<Long>

    fun getCitiesAndCurrentWeather(): Flow<List<CityAndCurrentWeather>>
}