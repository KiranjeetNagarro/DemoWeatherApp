package com.example.domain.repository

import com.example.domain.common.ResponseState
import com.example.domain.entity.WeatherForecastEntity
import kotlinx.coroutines.flow.Flow

interface IWeatherRepository {
    fun getCityWeatherForecast(cityName: String): Flow<ResponseState<WeatherForecastEntity>>

    fun getCities(): Flow<List<String>>

    fun deleteCity(cityName:String) :Flow<Unit>

    fun deleteAllCities() :Flow<Unit>
}