package com.example.domain.usecase

import com.example.domain.common.ResponseState
import com.example.domain.entity.WeatherForecastEntity
import com.example.domain.repository.IWeatherRepository
import kotlinx.coroutines.flow.Flow

class WeatherUseCase(private val userRepo: IWeatherRepository) {
    fun getCityWeatherForecast(
        cityName: String
    ): Flow<ResponseState<WeatherForecastEntity>> {
        return userRepo.getCityWeatherForecast(cityName)
    }

    fun getCities():Flow<List<String>>{
        return userRepo.getCities()
    }

    fun deleteCity(cityName:String) :Flow<Unit>{
        return userRepo.deleteCity(cityName)
    }

    fun deleteAllCities():Flow<Unit>{
        return userRepo.deleteAllCities()
    }
}