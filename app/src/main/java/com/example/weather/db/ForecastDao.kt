package com.example.weather.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.example.weather.db.entity.ForecastEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ForecastDao {

    @Query("SELECT * FROM Forecast")
    fun getForecast(): Flow<ForecastEntity>

    @Insert(onConflict = REPLACE)
    suspend fun insertForecast(forecast: ForecastEntity)

    @Transaction
    suspend fun deleteAndInsert(forecast: ForecastEntity) {
        deleteAll()
        insertForecast(forecast)
    }

    @Query("DELETE FROM Forecast")
    suspend fun deleteAll()
}