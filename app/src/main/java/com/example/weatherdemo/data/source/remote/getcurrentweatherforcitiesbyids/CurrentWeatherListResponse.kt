package com.example.weatherdemo.data.source.remote.getcurrentweatherforcitiesbyids

import com.google.gson.annotations.SerializedName

data class CurrentWeatherListResponse(
    @SerializedName("cnt")
    val citiesCount: Int?,
    @SerializedName("list")
    val cityAndWeatherList: List<CityAndWeatherResponse>?
)