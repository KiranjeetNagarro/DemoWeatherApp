package com.example.data.datamodel

import com.google.gson.annotations.SerializedName

data class WeatherForecastModel(
    @SerializedName("list") val weatherData: List<WeatherDataModel>,
    @SerializedName("city") val city: CityModel
)

data class WeatherDataModel(
    @SerializedName("main") val main: MainModel,
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("dt_txt") val dtTxt: String
)

data class CityModel(
    @SerializedName("name") val name: String,
)

data class MainModel(
    @SerializedName("temp") val temp: Double,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("temp_max") val tempMax: Double,
    @SerializedName("humidity") val humidity: Int
)

data class Weather(
    @SerializedName("main") val mainInfo: String,
    @SerializedName("description") val description: String
)

data class Wind(@SerializedName("speed") val speed: Double)
