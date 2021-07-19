package com.example.weatherdemo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class City(
    @PrimaryKey
    val id: Long,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val country: String,
    @ColumnInfo(name = "timezone_offset_unix_seconds")
    val timezoneOffsetUnixSeconds: Long
)