package com.example.weatherapp.provider

import com.example.domain.repository.IWeatherRepository
import com.example.domain.usecase.WeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object UseCaseProviderModule {

    @Provides
    fun getUserUseCase(userRepository: IWeatherRepository): WeatherUseCase {
        return WeatherUseCase(userRepository)
    }
}