package com.example.weatherdemo.data.source.local

import com.example.weatherdemo.data.CurrentWeather
import com.example.weatherdemo.data.DailyWeather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRoomDataSource @Inject constructor(
    private val weatherDao: WeatherDao,
    private val ioDispatcher: CoroutineDispatcher
) : WeatherLocalDataSource {

    override fun getDailyWeatherList(): Flow<List<DailyWeather>> = weatherDao.getDailyWeatherList()

    override suspend fun saveCurrentWeather(currentWeather: CurrentWeather) =
        withContext(ioDispatcher) { weatherDao.insertCurrentWeather(currentWeather) }

    override suspend fun saveCurrentWeatherList(currentWeatherList: List<CurrentWeather>) =
        withContext(ioDispatcher) { weatherDao.insertCurrentWeatherList(currentWeatherList) }

    override suspend fun saveDailyWeatherList(cityId: Long, dailyWeatherList: List<DailyWeather>) =
        withContext(ioDispatcher) { weatherDao.replaceDailyWeatherFor(cityId, dailyWeatherList) }
}