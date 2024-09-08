package com.example.data.api

import com.example.data.datamodel.WeatherForecastModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiService {
    @GET("data/2.5/forecast")
    suspend fun getCityWeatherForecast(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String
    ): Response<WeatherForecastModel>
}