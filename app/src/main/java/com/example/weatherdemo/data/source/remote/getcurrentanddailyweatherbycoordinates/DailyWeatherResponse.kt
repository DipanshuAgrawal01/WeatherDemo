package com.example.weatherdemo.data.source.remote.getcurrentanddailyweatherbycoordinates

import com.example.weatherdemo.data.source.remote.getcurrentweatherforcitybyname.WeatherResponse
import com.google.gson.annotations.SerializedName

data class DailyWeatherResponse(
    @SerializedName("dt")
    val dateTimeUnixSeconds: Long?,
    @SerializedName("sunrise")
    val sunriseUnixSeconds: Long?,
    @SerializedName("sunset")
    val sunsetUnixSeconds: Long?,
    val weather: List<WeatherResponse>?,
    val pressure: Int?,
    val humidity: Int?,
    @SerializedName("temp")
    val temperature: TemperatureResponse?,
    @SerializedName("feels_like")
    val feelsLikeTemperature: TemperatureResponse?,
    @SerializedName("wind_speed")
    val windSpeed: Double?,
    @SerializedName("wind_deg")
    val windDegree: Double?
)