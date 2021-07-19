package com.example.weatherdemo.data.source.remote.getcurrentanddailyweatherbycoordinates

import com.google.gson.annotations.SerializedName

data class CurrentAndDailyWeatherResponse(
    @SerializedName("current")
    val currentWeather: CurrentWeatherResponse?,
    @SerializedName("daily")
    val dailyWeatherList: List<DailyWeatherResponse>?
)