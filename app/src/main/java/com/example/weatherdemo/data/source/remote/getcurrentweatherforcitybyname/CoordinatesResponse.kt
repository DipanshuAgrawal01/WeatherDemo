package com.example.weatherdemo.data.source.remote.getcurrentweatherforcitybyname

import com.google.gson.annotations.SerializedName

data class CoordinatesResponse(
    @SerializedName("lat")
    val latitude: Double?,
    @SerializedName("lon")
    val longitude: Double?
)