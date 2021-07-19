package com.example.weatherdemo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_weather")
data class CurrentWeather(
    @PrimaryKey
    @ColumnInfo(name = "city_id")
    val cityId: Long,
    @ColumnInfo(name = "date_time_unix_seconds")
    val dateTimeUnixSeconds: Long,
    val description: String,
    @ColumnInfo(name = "icon_url")
    val iconUrl: String,
    @ColumnInfo(name = "sunrise_unix_seconds")
    val sunriseUnixSeconds: Long,
    @ColumnInfo(name = "sunset_unix_seconds")
    val sunsetUnixSeconds: Long,
    val pressure: Int,
    val humidity: Int,
    val visibility: Int,
    @ColumnInfo(name = "wind_speed")
    val windSpeed: Double,
    @ColumnInfo(name = "wind_degree")
    val windDegree: Double,
    val temperature: Double,
    @ColumnInfo(name = "feels_like_temperature")
    val feelsLikeTemperature: Double
)