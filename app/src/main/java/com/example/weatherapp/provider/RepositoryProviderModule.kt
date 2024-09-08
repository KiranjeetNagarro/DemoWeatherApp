package com.example.weatherapp.provider

import com.example.data.api.IApiService
import com.example.data.db.AppDatabase
import com.example.data.repository.WeatherRepository
import com.example.domain.repository.IWeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryProviderModule {

    @Provides
    fun getWeatherRepository(apiService: IApiService, db:AppDatabase ): IWeatherRepository {
        return WeatherRepository(apiService,db)
    }
}