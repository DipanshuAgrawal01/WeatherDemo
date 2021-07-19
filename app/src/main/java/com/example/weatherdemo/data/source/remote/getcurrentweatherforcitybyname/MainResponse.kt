package com.example.weatherdemo.data.source.remote.getcurrentweatherforcitybyname

import com.google.gson.annotations.SerializedName

data class MainResponse(
    val pressure: Int?,
    val humidity: Int?,
    @SerializedName("temp")
    val temperature: Double?,
    @SerializedName("feels_like")
    val feelsLikeTemperature: Double?
)