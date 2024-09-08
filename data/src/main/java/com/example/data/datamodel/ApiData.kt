package com.example.data.datamodel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class ApiData(
    @PrimaryKey val cityName: String,
    val data: String
)