package com.example.weatherapp.extension

import com.example.domain.entity.WeatherDataEntity
import com.example.weatherapp.R
import com.example.weatherapp.uidatamodel.WeatherDetailUIModel

fun WeatherDataEntity.toDataModel(): WeatherDetailUIModel {
    return WeatherDetailUIModel(
        icon = getIcon(weather.first().mainInfo),
        day = getDayOfMonthString(dtTxt) ?: "",
        minTemp = main.tempMin.toString().substringBefore(".") + "°C",
        maxTemp = main.tempMax.toString().substringBefore(".") + "°C",
        desc = weather.first().description
    )
}

fun getIcon(info: String): Int {
    return when (info) {
        CLOUDS -> {
            R.drawable.cloud
        }

        RAIN -> {
            R.drawable.rainy
        }

        else -> {
            R.drawable.sunny
        }
    }
}

private const val RAIN = "Rain"
private const val CLOUDS = "Clouds"




