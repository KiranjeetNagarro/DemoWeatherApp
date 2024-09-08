package com.example.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.datamodel.ApiData

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(apiData: ApiData)

    @Query("SELECT * FROM weather WHERE cityName = :cityName")
    suspend fun getApiData(cityName: String): ApiData?

    @Query("SELECT cityName FROM weather")
    suspend fun getCities(): List<String>

    @Query("DELETE FROM weather WHERE cityName = :cityName")
    suspend fun deleteCityName(cityName: String)

    @Query("DELETE FROM weather")
    suspend fun deleteAllCities()
}