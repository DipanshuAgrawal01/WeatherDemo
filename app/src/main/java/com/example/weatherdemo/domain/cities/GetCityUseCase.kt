package com.example.weatherdemo.domain.cities

import com.example.weatherdemo.data.City
import com.example.weatherdemo.data.source.CitiesRepository
import javax.inject.Inject

class GetCityUseCase @Inject constructor(private val citiesRepository: CitiesRepository) {

    suspend operator fun invoke(cityId: Long): City = citiesRepository.getCity(cityId)
}