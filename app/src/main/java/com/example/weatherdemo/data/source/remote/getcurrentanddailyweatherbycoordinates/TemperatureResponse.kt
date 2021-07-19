package com.example.weatherdemo.data.source.remote.getcurrentanddailyweatherbycoordinates

import com.google.gson.annotations.SerializedName

data class TemperatureResponse(
    val night: Double?,
    @SerializedName("morn")
    val morning: Double?,
    val day: Double?,
    @SerializedName("eve")
    val evening: Double?,
    val min: Double?,
    val max: Double?
)