package com.example.weather.remote

import com.example.weather.models.ForecastData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("forecast")
    suspend fun getForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): ForecastData
}