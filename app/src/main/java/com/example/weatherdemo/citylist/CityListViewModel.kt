package com.example.weatherdemo.citylist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.weatherdemo.data.CityAndCurrentWeather
import com.example.weatherdemo.data.source.remote.Result
import com.example.weatherdemo.domain.cities.GetCitiesAndCurrentWeatherUseCase
import com.example.weatherdemo.domain.cities.GetCitiesIdsUseCase
import com.example.weatherdemo.domain.weather.RequestCurrentWeatherForCitiesByIdsUseCase
import com.example.weatherdemo.util.Event
import com.example.weatherdemo.util.composeString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CityListViewModel @ViewModelInject constructor(
    getCitiesAndCurrentWeatherUseCase: GetCitiesAndCurrentWeatherUseCase,
    val getCitiesIdsUseCase: GetCitiesIdsUseCase,
    val requestCurrentWeatherForCitiesByIdsUseCase: RequestCurrentWeatherForCitiesByIdsUseCase
) : ViewModel() {

    val citiesAndCurrentWeather: LiveData<List<CityAndCurrentWeather>> =
        getCitiesAndCurrentWeatherUseCase()
            .onEach {
                if (it.isNotEmpty() && it.find { it.currentWeather != null } == null) {
                    requestCurrentWeatherForCitiesByIdsUseCase(it.map { it.city.id }
                        .composeString())
                }
            }
            .flowOn(Dispatchers.IO)
            .asLiveData()

    private val _message = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>> = _message

    private val _openCityInfoCommand = MutableLiveData<Event<Long>>()
    val openCityInfoCommand: LiveData<Event<Long>> = _openCityInfoCommand

    fun onCityListFragmentStarted() = requestCurrentWeatherForCities()

    fun onCityClicked(cityId: Long) {
        _openCityInfoCommand.value = Event(cityId)
    }

    fun onNetworkRestored() = requestCurrentWeatherForCities()

    private fun requestCurrentWeatherForCities() {
        viewModelScope.launch {
            val citiesIds = getCitiesIdsUseCase()
            if (citiesIds.isNotEmpty()) {
                val requestResult = requestCurrentWeatherForCitiesByIdsUseCase(citiesIds)
                if (requestResult is Result.Error) _message.value = Event(requestResult.getString())
            }
        }
    }
}