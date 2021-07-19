package com.example.weatherdemo.domain.weather

import com.example.weatherdemo.data.DailyWeather
import com.example.weatherdemo.data.source.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDailyWeatherListUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {

    operator fun invoke(): Flow<List<DailyWeather>> = weatherRepository.getDailyWeatherList()
}