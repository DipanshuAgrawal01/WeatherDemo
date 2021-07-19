package com.example.weatherdemo.domain.cities

import com.example.weatherdemo.data.CityAndCurrentWeather
import com.example.weatherdemo.data.source.CitiesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCitiesAndCurrentWeatherUseCase @Inject constructor(private val citiesRepository: CitiesRepository) {

    operator fun invoke(): Flow<List<CityAndCurrentWeather>> =
        citiesRepository.getCitiesAndCurrentWeather()
}