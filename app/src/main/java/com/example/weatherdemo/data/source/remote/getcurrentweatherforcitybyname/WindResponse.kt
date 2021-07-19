package com.example.weatherdemo.data.source.remote.getcurrentweatherforcitybyname

import com.google.gson.annotations.SerializedName

data class WindResponse(
    val speed: Double?,
    @SerializedName("deg")
    val degree: Double?,
)