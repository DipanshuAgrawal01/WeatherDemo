package com.example.weatherdemo.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity

@Entity(tableName = "daily_weather", primaryKeys = ["city_id", "date_time_unix_seconds"])
data class DailyWeather(
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
    @ColumnInfo(name = "wind_speed")
    val windSpeed: Double,
    @ColumnInfo(name = "wind_degree")
    val windDegree: Double,
    @Embedded(prefix = "temperature_")
    val temperature: Temperature,
    @Embedded(prefix = "feels_like_temperature_")
    val feelsLikeTemperature: Temperature
)