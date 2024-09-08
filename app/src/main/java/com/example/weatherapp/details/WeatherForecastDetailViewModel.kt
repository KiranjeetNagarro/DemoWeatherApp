package com.example.weatherapp.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.entity.WeatherForecastEntity

class WeatherForecastDetailViewModel : ViewModel() {
    val temp = MutableLiveData<String>()
    val humidity = MutableLiveData<String>()
    val windSpeed = MutableLiveData<String>()
    val weatherDesc = MutableLiveData<String>()
    fun setData(entity: WeatherForecastEntity) {
        val currentWeather = entity.weatherData.first()
        temp.value = "${currentWeather.main.temp.toString().substringBefore(".")} °C"
        humidity.value = "Humidity: ${currentWeather.main.humidity} %"
        windSpeed.value = "${currentWeather.wind.speed} km/h"
        weatherDesc.value = currentWeather.weather.first().description
    }
}