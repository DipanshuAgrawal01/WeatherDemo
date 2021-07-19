package com.example.weatherdemo.domain.cities

import com.example.weatherdemo.data.source.CitiesRepository
import com.example.weatherdemo.util.composeString
import javax.inject.Inject

class GetCitiesIdsUseCase @Inject constructor(private val citiesRepository: CitiesRepository) {

    suspend operator fun invoke(): String = citiesRepository.getCitiesIds().composeString()
}