package com.example.weatherdemo.data

import androidx.room.ColumnInfo

data class Temperature(
    @ColumnInfo(name = "night")
    val night: Double,
    @ColumnInfo(name = "morning")
    val morning: Double,
    @ColumnInfo(name = "day")
    val day: Double,
    @ColumnInfo(name = "evening")
    val evening: Double,
    @ColumnInfo(name = "min")
    val min: Double,
    @ColumnInfo(name = "max")
    val max: Double
)