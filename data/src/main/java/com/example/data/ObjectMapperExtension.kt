package com.example.data

import com.example.data.datamodel.CityModel
import com.example.data.datamodel.MainModel
import com.example.data.datamodel.Weather
import com.example.data.datamodel.WeatherDataModel
import com.example.data.datamodel.WeatherForecastModel
import com.example.data.datamodel.Wind
import com.example.domain.entity.CityEntity
import com.example.domain.entity.MainEntity
import com.example.domain.entity.WeatherDataEntity
import com.example.domain.entity.WeatherEntity
import com.example.domain.entity.WeatherForecastEntity
import com.example.domain.entity.WindEntity

fun WeatherForecastModel.toWeatherForecastEntity(): WeatherForecastEntity {
    return WeatherForecastEntity(
        weatherData = weatherData.map { it.toWeatherDataEntity() },
        city = city.toCityEntity()
    )
}

fun WeatherDataModel.toWeatherDataEntity() =
    WeatherDataEntity(
        main = main.toMainEntity(),
        weather = weather.map { it.toWeatherEntity() },
        wind = wind.toWindEntity(),
        dtTxt = dtTxt
    )

fun MainModel.toMainEntity() =
    MainEntity(temp = temp, tempMin = tempMin, tempMax = tempMax, humidity = humidity)

fun Weather.toWeatherEntity() = WeatherEntity(mainInfo = mainInfo, description = description)

fun Wind.toWindEntity() = WindEntity(speed = speed)

fun CityModel.toCityEntity(): CityEntity = CityEntity(name = name)

