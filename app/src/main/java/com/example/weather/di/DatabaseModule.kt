package com.example.weather.di

import android.content.Context
import androidx.room.Room
import com.example.weather.db.ForecastDao
import com.example.weather.db.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideImageDao(appDatabase: WeatherDatabase): ForecastDao {
        return appDatabase.forecastDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): WeatherDatabase {
        return Room.databaseBuilder(
            appContext,
            WeatherDatabase::class.java,
            "Weather"
        ).build()
    }
}