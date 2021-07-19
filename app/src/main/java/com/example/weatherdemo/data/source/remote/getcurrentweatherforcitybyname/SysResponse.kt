package com.example.weatherdemo.data.source.remote.getcurrentweatherforcitybyname

import com.google.gson.annotations.SerializedName

data class SysResponse(
    val country: String?,
    @SerializedName("sunrise")
    val sunriseUnixSeconds: Long?,
    @SerializedName("sunset")
    val sunsetUnixSeconds: Long?
)