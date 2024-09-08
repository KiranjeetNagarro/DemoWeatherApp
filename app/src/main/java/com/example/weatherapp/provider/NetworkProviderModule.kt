package com.example.weatherapp.provider

import com.example.data.api.IApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object NetworkProviderModule {

    private const val BASE_URL = "https://api.openweathermap.org"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun getNetworkService(): IApiService {
        return retrofit.create(IApiService::class.java)
    }
}