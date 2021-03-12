package com.example.weather.repo

import com.example.weather.db.ForecastDao
import com.example.weather.db.entity.ForecastEntity
import com.example.weather.models.Forecast
import com.example.weather.models.ForecastData
import com.example.weather.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ForecastRepository @Inject constructor(private val forecastDao: ForecastDao,
private val apiService: ApiService){

    fun getForecast() = forecastDao.getForecast()

    suspend fun insertForecast(forecastData: ForecastData) = forecastDao.deleteAndInsert(ForecastEntity(forecastData))

    suspend fun fetchData(): Flow<ForecastData> {
        return flow {
            emit(apiService.getForecast(1.3521, 103.8198))
        }.flowOn(Dispatchers.IO)
    }
}