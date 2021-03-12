package com.example.weather.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.weather.models.Forecast
import com.example.weather.models.ForecastData
import java.util.*

@Entity(tableName = "Forecast")
data class ForecastEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "date")
    var date: Date,

    @ColumnInfo(name = "forecastList")
    var forecastList: List<Forecast>?
) {

    @Ignore
    constructor(forecastData: ForecastData) : this(
        id = 0,
        date = Date(),
        forecastList = forecastData.list
    )
}