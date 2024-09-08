package com.example.weatherapp.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.ResponseState
import com.example.domain.entity.WeatherForecastEntity
import com.example.domain.usecase.WeatherUseCase
import com.example.weatherapp.common.SingleLiveEvent
import com.example.weatherapp.uidatamodel.CityModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SearchWeatherForecastViewModel @Inject constructor(private val weatherUseCase: WeatherUseCase) :
    ViewModel() {

    val cityName = MutableLiveData<String>()
    val isLoading = MutableLiveData(false)
    val error = SingleLiveEvent<String>()
    val deleteSuccessEvent = SingleLiveEvent<Boolean>()
    val weatherResponse = SingleLiveEvent<WeatherForecastEntity?>()
    val cities = MutableLiveData<List<CityModel>>()


    fun fetchCities() {
        viewModelScope.launch {
            withContext(IO) {
                weatherUseCase.getCities().collect { list ->
                    val cityModelList = list.map {
                        CityModel(cityName = it)
                    }
                    cities.postValue(cityModelList)
                }
            }
        }
    }

    fun deleteCity(cityName: String) {
        viewModelScope.launch {
            withContext(IO) {
                weatherUseCase.deleteCity(cityName).collect {
                    deleteSuccessEvent.postValue(true)
                }
            }
        }
    }

    fun getWeatherForecast(cityName: String) {
        isLoading.postValue(true)
        viewModelScope.launch {
            withContext(IO) {
                weatherUseCase.getCityWeatherForecast(cityName).collect {
                    when (it) {
                        is ResponseState.Success -> {
                            isLoading.postValue(false)
                            fetchCities()
                            weatherResponse.postValue(it.data)
                        }

                        is ResponseState.Error -> {
                            isLoading.postValue(false)
                            error.postValue(it.error.errorMessage)
                        }
                    }
                }
            }
        }
    }

    fun deleteAllCities() {
        viewModelScope.launch {
            withContext(IO) {
                weatherUseCase.deleteAllCities().collect {
                    deleteSuccessEvent.postValue(true)
                }
            }
        }
    }

}