package com.example.weatherdemo.domain.weather

import com.example.weatherdemo.data.source.WeatherRepository
import com.example.weatherdemo.data.source.remote.Result
import com.example.weatherdemo.util.METRIC_MEASURE_UNITS
import com.example.weatherdemo.util.RUSSIAN_LANGUAGE
import com.example.weatherdemo.util.WEATHER_API_KEY
import javax.inject.Inject

class RequestCurrentWeatherForCityByNameUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {

    suspend operator fun invoke(
        cityName: String,
        measureUnits: String = METRIC_MEASURE_UNITS,
        responseLanguage: String = RUSSIAN_LANGUAGE,
        weatherApiKey: String = WEATHER_API_KEY
    ): Result<Unit> = weatherRepository.requestCurrentWeatherForCityByName(
        cityName,
        measureUnits,
        responseLanguage,
        weatherApiKey
    )
}