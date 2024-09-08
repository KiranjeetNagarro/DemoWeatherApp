package com.example.weatherapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.entity.CityEntity
import com.example.domain.entity.MainEntity
import com.example.domain.entity.WeatherDataEntity
import com.example.domain.entity.WeatherEntity
import com.example.domain.entity.WeatherForecastEntity
import com.example.domain.entity.WindEntity
import com.example.weatherapp.details.WeatherForecastDetailViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class WeatherForecastDetailViewModelTest {

    // Rule to allow LiveData to be tested synchronously
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: WeatherForecastDetailViewModel

    @Before
    fun setUp() {
        viewModel = WeatherForecastDetailViewModel()
    }

    @Test
    fun testSetData() {
        // When
        viewModel.setData(mockWeatherEntity)

        // Then
        assert(viewModel.temp.value == "23 °C")
        assert(viewModel.humidity.value == "Humidity: 78 %")
        assert(viewModel.windSpeed.value == "10.5 km/h")
        assert(viewModel.weatherDesc.value == "broken clouds")
    }

    companion object{
         val  mockWeatherEntity = WeatherForecastEntity(
            weatherData = listOf(
                WeatherDataEntity(
                    main = MainEntity(temp = 23.4, tempMin = 20.0, tempMax = 28.0, humidity = 78),
                    wind = WindEntity(speed = 10.5),
                    weather = listOf(
                        WeatherEntity(
                            mainInfo = "Clouds",
                            description = "broken clouds"
                        )
                    ),
                    dtTxt = "2024-09-08 18:00:00"

                )
            ),
            city = CityEntity("New York")
        )
    }
}