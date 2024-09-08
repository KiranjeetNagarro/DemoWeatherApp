package com.example.weatherapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asFlow
import com.example.data.repository.WeatherRepository
import com.example.domain.common.ResponseState
import com.example.domain.entity.Entity
import com.example.domain.usecase.WeatherUseCase
import com.example.weatherapp.WeatherForecastDetailViewModelTest.Companion.mockWeatherEntity
import com.example.weatherapp.search.SearchWeatherForecastViewModel
import com.example.weatherapp.uidatamodel.CityModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class SearchWeatherForecastViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var weatherRepository: WeatherRepository

    private lateinit var viewModel: SearchWeatherForecastViewModel
    private lateinit var weatherUseCase: WeatherUseCase
    private val testDispatcher = StandardTestDispatcher()
    private val citiesList = listOf("New York", "Los Angeles", "Chicago")


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        // Set the Main dispatcher to a TestDispatcher
        Dispatchers.setMain(testDispatcher)
        weatherUseCase = WeatherUseCase(weatherRepository)
        viewModel = SearchWeatherForecastViewModel(weatherUseCase)
        Mockito.`when`(weatherUseCase.getCities()).thenReturn(flowOf(citiesList))
    }

    @After
    fun tearDown() {
        // Reset Main dispatcher to the original dispatcher after the test
        Dispatchers.resetMain()
    }

    @Test
    fun testFetchCities() = runTest(testDispatcher) {
        // Given
        Mockito.`when`(weatherUseCase.getCities()).thenReturn(flowOf(citiesList))

        // When
        viewModel.fetchCities()

        val result = viewModel.cities.asFlow().first()

        // Then
        assert(result == citiesList.map { CityModel(cityName = it) })
    }

    @Test
    fun testDeleteCity() = runTest(testDispatcher) {
        // Given
        val cityName = "New York"
        Mockito.`when`(weatherUseCase.deleteCity(cityName)).thenReturn(flowOf(Unit))

        // When
        viewModel.deleteCity(cityName)

        // Then
        val result = viewModel.deleteSuccessEvent.asFlow().first()
        assert(result == true)
    }

    @Test
    fun testGetWeatherForecastSuccess() = runTest(testDispatcher) {
        // Given
        val cityName = "New York"
        Mockito.`when`(weatherUseCase.getCityWeatherForecast(cityName))
            .thenReturn(flowOf(ResponseState.Success(mockWeatherEntity)))

        // When
        viewModel.getWeatherForecast(cityName)

        val result = viewModel.weatherResponse.asFlow().first()

        // Then
        assert(result == mockWeatherEntity)
        assert(viewModel.isLoading.value == false)
    }

    @Test
    fun testGetWeatherForecastFailure() = runTest(testDispatcher) {
        // Given
        val cityName = "New York"
        val errorCode = 400
        val errorMessage = "Failed to fetch weather data"
        Mockito.`when`(weatherUseCase.getCityWeatherForecast(cityName))
            .thenReturn(flowOf(ResponseState.Error(Entity.Error(errorCode, errorMessage))))

        // When
        viewModel.getWeatherForecast(cityName)

        val result = viewModel.error.asFlow().first()
        val loadingStatus = viewModel.isLoading.asFlow().first()

        // Then
        assert(result == errorMessage)
        assert(loadingStatus == false)
    }

    @Test
    fun testDeleteAllCities() = runTest(testDispatcher) {
        // Given
        Mockito.`when`(weatherUseCase.deleteAllCities()).thenReturn(flowOf(Unit))
        // When
        viewModel.deleteAllCities()
        // Then
        val result = viewModel.deleteSuccessEvent.asFlow().first()
        assert(result == true)
    }
}