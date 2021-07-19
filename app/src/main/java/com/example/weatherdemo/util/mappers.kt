package com.example.weatherdemo.util

import com.example.weatherdemo.data.City
import com.example.weatherdemo.data.CurrentWeather
import com.example.weatherdemo.data.DailyWeather
import com.example.weatherdemo.data.Temperature
import com.example.weatherdemo.data.source.remote.getcurrentanddailyweatherbycoordinates.CurrentWeatherResponse
import com.example.weatherdemo.data.source.remote.getcurrentanddailyweatherbycoordinates.DailyWeatherResponse
import com.example.weatherdemo.data.source.remote.getcurrentweatherforcitiesbyids.CityAndWeatherResponse
import com.example.weatherdemo.data.source.remote.getcurrentweatherforcitybyname.CurrentWeatherForCityResponse

fun CityAndWeatherResponse.toCurrentWeatherEntity(): CurrentWeather = CurrentWeather(
    cityId = cityId ?: NO_VALUE,
    dateTimeUnixSeconds = dateTimeUnixSeconds ?: NO_VALUE,
    description = weather?.getOrNull(0)?.description.orEmpty(),
    iconUrl = if (weather?.getOrNull(0)?.iconUrlLastSegment.isNullOrEmpty()) {
        ""
    } else {
        WEATHER_ICON_URL_FIRST_PART + weather?.getOrNull(0)?.iconUrlLastSegment + "@2x.png"
    },
    sunriseUnixSeconds = sys?.sunriseUnixSeconds ?: NO_VALUE,
    sunsetUnixSeconds = sys?.sunsetUnixSeconds ?: NO_VALUE,
    pressure = main?.pressure ?: NO_VALUE.toInt(),
    humidity = main?.humidity ?: NO_VALUE.toInt(),
    visibility = visibility ?: NO_VALUE.toInt(),
    windSpeed = wind?.speed ?: NO_VALUE.toDouble(),
    windDegree = wind?.degree ?: NO_VALUE.toDouble(),
    temperature = main?.temperature ?: NO_VALUE.toDouble(),
    feelsLikeTemperature = main?.feelsLikeTemperature ?: NO_VALUE.toDouble()
)

fun CurrentWeatherForCityResponse.toCityEntity(): City = City(
    id = cityId ?: NO_VALUE,
    latitude = cityCoordinates?.latitude ?: NO_VALUE.toDouble(),
    longitude = cityCoordinates?.longitude ?: NO_VALUE.toDouble(),
    name = cityName.orEmpty(),
    country = sys?.country.orEmpty(),
    timezoneOffsetUnixSeconds = cityTimezoneOffsetUnixSeconds ?: NO_VALUE
)

fun CurrentWeatherForCityResponse.toCurrentWeatherEntity(): CurrentWeather = CurrentWeather(
    cityId = cityId ?: NO_VALUE,
    dateTimeUnixSeconds = dateTimeUnixSeconds ?: NO_VALUE,
    description = weather?.getOrNull(0)?.description.orEmpty(),
    iconUrl = if (weather?.getOrNull(0)?.iconUrlLastSegment.isNullOrEmpty()) {
        ""
    } else {
        WEATHER_ICON_URL_FIRST_PART + weather?.getOrNull(0)?.iconUrlLastSegment + "@2x.png"
    },
    sunriseUnixSeconds = sys?.sunriseUnixSeconds ?: NO_VALUE,
    sunsetUnixSeconds = sys?.sunsetUnixSeconds ?: NO_VALUE,
    pressure = main?.pressure ?: NO_VALUE.toInt(),
    humidity = main?.humidity ?: NO_VALUE.toInt(),
    visibility = visibility ?: NO_VALUE.toInt(),
    windSpeed = wind?.speed ?: NO_VALUE.toDouble(),
    windDegree = wind?.degree ?: NO_VALUE.toDouble(),
    temperature = main?.temperature ?: NO_VALUE.toDouble(),
    feelsLikeTemperature = main?.feelsLikeTemperature ?: NO_VALUE.toDouble()
)

fun CurrentWeatherResponse.toCurrentWeatherEntity(cityId: Long): CurrentWeather = CurrentWeather(
    cityId = cityId,
    dateTimeUnixSeconds = dateTimeUnixSeconds ?: NO_VALUE,
    description = weather?.getOrNull(0)?.description.orEmpty(),
    iconUrl = if (weather?.getOrNull(0)?.iconUrlLastSegment.isNullOrEmpty()) {
        ""
    } else {
        WEATHER_ICON_URL_FIRST_PART + weather?.getOrNull(0)?.iconUrlLastSegment + "@2x.png"
    },
    sunriseUnixSeconds = sunriseUnixSeconds ?: NO_VALUE,
    sunsetUnixSeconds = sunsetUnixSeconds ?: NO_VALUE,
    pressure = pressure ?: NO_VALUE.toInt(),
    humidity = humidity ?: NO_VALUE.toInt(),
    visibility = visibility ?: NO_VALUE.toInt(),
    windSpeed = windSpeed ?: NO_VALUE.toDouble(),
    windDegree = windDegree ?: NO_VALUE.toDouble(),
    temperature = temperature ?: NO_VALUE.toDouble(),
    feelsLikeTemperature = feelsLikeTemperature ?: NO_VALUE.toDouble()
)

fun DailyWeatherResponse.toDailyWeatherEntity(cityId: Long): DailyWeather = DailyWeather(
    cityId = cityId,
    dateTimeUnixSeconds = dateTimeUnixSeconds ?: NO_VALUE,
    description = weather?.getOrNull(0)?.description.orEmpty(),
    iconUrl = if (weather?.getOrNull(0)?.iconUrlLastSegment.isNullOrEmpty()) {
        ""
    } else {
        WEATHER_ICON_URL_FIRST_PART + weather?.getOrNull(0)?.iconUrlLastSegment + "@2x.png"
    },
    sunriseUnixSeconds = sunriseUnixSeconds ?: NO_VALUE,
    sunsetUnixSeconds = sunsetUnixSeconds ?: NO_VALUE,
    pressure = pressure ?: NO_VALUE.toInt(),
    humidity = humidity ?: NO_VALUE.toInt(),
    windSpeed = windSpeed ?: NO_VALUE.toDouble(),
    windDegree = windDegree ?: NO_VALUE.toDouble(),
    temperature = Temperature(
        temperature?.night ?: NO_VALUE.toDouble(),
        temperature?.morning ?: NO_VALUE.toDouble(),
        temperature?.day ?: NO_VALUE.toDouble(),
        temperature?.evening ?: NO_VALUE.toDouble(),
        temperature?.min ?: NO_VALUE.toDouble(),
        temperature?.max ?: NO_VALUE.toDouble()
    ),
    feelsLikeTemperature = Temperature(
        feelsLikeTemperature?.night ?: NO_VALUE.toDouble(),
        feelsLikeTemperature?.morning ?: NO_VALUE.toDouble(),
        feelsLikeTemperature?.day ?: NO_VALUE.toDouble(),
        feelsLikeTemperature?.evening ?: NO_VALUE.toDouble(),
        feelsLikeTemperature?.min ?: NO_VALUE.toDouble(),
        feelsLikeTemperature?.max ?: NO_VALUE.toDouble()
    )
)