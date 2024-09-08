package com.example.data.repository

import com.example.data.api.IApiService
import com.example.data.datamodel.ApiData
import com.example.data.datamodel.WeatherForecastModel
import com.example.data.db.AppDatabase
import com.example.data.toWeatherForecastEntity
import com.example.data.util.API_KEY
import com.example.data.util.UNITS
import com.example.domain.common.ResponseState
import com.example.domain.entity.Entity
import com.example.domain.entity.WeatherForecastEntity
import com.example.domain.repository.IWeatherRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherRepository(private val apiService: IApiService, private val db: AppDatabase) :
    IWeatherRepository {

    override fun getCityWeatherForecast(cityName: String): Flow<ResponseState<WeatherForecastEntity>> {
        return flow {
            try {
                val dao = db.weatherDao()
                val cacheData = dao.getApiData(cityName)
                if (cacheData != null) {
                    val weatherForecastModel =
                        Gson().fromJson(cacheData.data, WeatherForecastModel::class.java)
                    emit(ResponseState.Success(weatherForecastModel.toWeatherForecastEntity()))
                } else {
                    val response = apiService.getCityWeatherForecast(cityName, API_KEY, UNITS)
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body == null) {
                            emit(ResponseState.Success(null))
                        } else {
                            dao.insert(ApiData(cityName, Gson().toJson(body)))
                            emit(ResponseState.Success(body.toWeatherForecastEntity()))
                        }
                    } else {
                        emit(ResponseState.Error(Entity.Error(response.code(), response.message())))
                    }
                }
            } catch (exception: Exception) {
                emit(ResponseState.Error(Entity.Error(1, exception.message.orEmpty())))
            }
        }
    }

    override fun getCities(): Flow<List<String>> {
        return flow {
            emit(db.weatherDao().getCities())
        }
    }

    override fun deleteCity(cityName: String): Flow<Unit> {
        return flow {
            emit(db.weatherDao().deleteCityName(cityName))
        }
    }

    override fun deleteAllCities(): Flow<Unit> {
        return flow {
            emit(db.weatherDao().deleteAllCities())
        }
    }
}