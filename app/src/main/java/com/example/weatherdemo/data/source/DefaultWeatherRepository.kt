package com.example.weatherdemo.data.source

import com.example.weatherdemo.data.DailyWeather
import com.example.weatherdemo.data.source.local.CitiesLocalDataSource
import com.example.weatherdemo.data.source.local.WeatherLocalDataSource
import com.example.weatherdemo.data.source.remote.Result
import com.example.weatherdemo.data.source.remote.WeatherRemoteDataSource
import com.example.weatherdemo.util.toCityEntity
import com.example.weatherdemo.util.toCurrentWeatherEntity
import com.example.weatherdemo.util.toDailyWeatherEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultWeatherRepository @Inject constructor(
    private val citiesLocalDataSource: CitiesLocalDataSource,
    private val weatherLocalDataSource: WeatherLocalDataSource,
    private val weatherRemoteDataSource: WeatherRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : WeatherRepository {

    override fun getDailyWeatherList(): Flow<List<DailyWeather>> =
        weatherLocalDataSource.getDailyWeatherList()

    override suspend fun requestCurrentWeatherForCitiesByIds(
        idsString: String,
        measureUnits: String,
        responseLanguage: String,
        weatherApiKey: String
    ): Result<Unit> = withContext(ioDispatcher) {
        when (val currentWeatherForCitiesResult =
            weatherRemoteDataSource.getCurrentWeatherForCitiesByIds(
                idsString,
                measureUnits,
                responseLanguage,
                weatherApiKey
            )) {
            is Result.Success -> {
                val currentWeatherList =
                    currentWeatherForCitiesResult.data.cityAndWeatherList?.map { it.toCurrentWeatherEntity() }
                if (!currentWeatherList.isNullOrEmpty()) {
                    weatherLocalDataSource.saveCurrentWeatherList(currentWeatherList)
                }
                Result.Success(Unit)
            }
            is Result.Error -> {
                Result.Error(currentWeatherForCitiesResult.exception)
            }
        }
    }

    override suspend fun requestCurrentWeatherForCityByName(
        cityName: String,
        measureUnits: String,
        responseLanguage: String,
        weatherApiKey: String
    ): Result<Unit> = withContext(ioDispatcher) {
        when (val currentWeatherForCityResult =
            weatherRemoteDataSource.getCurrentWeatherForCityByName(
                cityName,
                measureUnits,
                responseLanguage,
                weatherApiKey
            )) {
            is Result.Success -> {
                val city = currentWeatherForCityResult.data.toCityEntity()
                val currentWeather = currentWeatherForCityResult.data.toCurrentWeatherEntity()
                citiesLocalDataSource.saveCity(city)
                weatherLocalDataSource.saveCurrentWeather(currentWeather)
                Result.Success(Unit)
            }
            is Result.Error -> {
                Result.Error(currentWeatherForCityResult.exception)
            }
        }
    }

    override suspend fun requestCurrentAndDailyWeatherByCoordinates(
        cityId: Long,
        latitude: Double,
        longitude: Double,
        excludeFields: String,
        measureUnits: String,
        responseLanguage: String,
        weatherApiKey: String
    ): Result<Unit> = withContext(ioDispatcher) {
        when (val currentAndDailyWeatherResult =
            weatherRemoteDataSource.getCurrentAndDailyWeatherByCoordinates(
                latitude,
                longitude,
                excludeFields,
                measureUnits,
                responseLanguage,
                weatherApiKey
            )) {
            is Result.Success -> {
                val currentWeather =
                    currentAndDailyWeatherResult.data.currentWeather?.toCurrentWeatherEntity(cityId)
                val dailyWeatherList =
                    currentAndDailyWeatherResult.data.dailyWeatherList?.map {
                        it.toDailyWeatherEntity(cityId)
                    }
                currentWeather?.let { weatherLocalDataSource.saveCurrentWeather(it) }
                dailyWeatherList?.let { weatherLocalDataSource.saveDailyWeatherList(cityId, it) }
                Result.Success(Unit)
            }
            is Result.Error -> {
                Result.Error(currentAndDailyWeatherResult.exception)
            }
        }
    }
}

