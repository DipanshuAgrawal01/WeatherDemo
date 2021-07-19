package com.example.weatherdemo.data.source.remote.getcurrentweatherforcitybyname

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val description: String?,
    @SerializedName("icon")
    val iconUrlLastSegment: String?
)