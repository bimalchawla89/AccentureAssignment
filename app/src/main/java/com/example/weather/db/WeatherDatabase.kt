package com.example.weather.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weather.db.entity.ForecastEntity
import com.example.weather.utils.DataConverter

@Database(
    entities = [
        ForecastEntity::class,
    ],
    version = 1
)
@TypeConverters(DataConverter::class)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun forecastDao(): ForecastDao
}