package com.example.weatherdemo.data.source.local

import com.example.weatherdemo.data.City
import com.example.weatherdemo.data.CityAndCurrentWeather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CitiesRoomDataSource @Inject constructor(
    private val citiesDao: CitiesDao,
    private val ioDispatcher: CoroutineDispatcher
) : CitiesLocalDataSource {

    override suspend fun getCity(cityId: Long): City =
        withContext(ioDispatcher) { citiesDao.getCity(cityId) }

    override suspend fun getCitiesIds(): List<Long> =
        withContext(ioDispatcher) { citiesDao.getCitiesIds() }

    override fun getCitiesAndCurrentWeather(): Flow<List<CityAndCurrentWeather>> =
        citiesDao.getCitiesAndCurrentWeather()

    override suspend fun saveCity(city: City) =
        withContext(ioDispatcher) { citiesDao.insertCity(city) }
}