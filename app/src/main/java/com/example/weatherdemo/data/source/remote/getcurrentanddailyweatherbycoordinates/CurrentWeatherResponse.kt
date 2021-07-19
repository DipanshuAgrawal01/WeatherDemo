package com.example.weatherdemo.data.source.remote.getcurrentanddailyweatherbycoordinates

import com.example.weatherdemo.data.source.remote.getcurrentweatherforcitybyname.WeatherResponse
import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
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
    val temperature: Double?,
    @SerializedName("feels_like")
    val feelsLikeTemperature: Double?,
    val visibility: Int?,
    @SerializedName("wind_speed")
    val windSpeed: Double?,
    @SerializedName("wind_deg")
    val windDegree: Double?
)