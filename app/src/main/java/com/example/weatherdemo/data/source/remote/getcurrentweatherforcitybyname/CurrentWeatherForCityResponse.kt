package com.example.weatherdemo.data.source.remote.getcurrentweatherforcitybyname

import com.google.gson.annotations.SerializedName

data class CurrentWeatherForCityResponse(
    @SerializedName("id")
    val cityId: Long?,
    @SerializedName("coord")
    val cityCoordinates: CoordinatesResponse?,
    @SerializedName("name")
    val cityName: String?,
    @SerializedName("timezone")
    val cityTimezoneOffsetUnixSeconds: Long?,
    val sys: SysResponse?,
    @SerializedName("dt")
    val dateTimeUnixSeconds: Long?,
    val weather: List<WeatherResponse>?,
    val main: MainResponse?,
    val visibility: Int?,
    val wind: WindResponse?
)