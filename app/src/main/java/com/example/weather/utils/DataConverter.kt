package com.example.weather.utils

import androidx.room.TypeConverter
import com.example.weather.models.Forecast
import com.example.weather.models.WeatherItem
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.*


object DataConverter {

    val jsonConfiguration by lazy {
        Json { isLenient = true; ignoreUnknownKeys = true; allowSpecialFloatingPointValues = true }
    }

    inline fun <reified T> parseResponse(response: String?): T? {
        return response?.let {
            jsonConfiguration.decodeFromString(it)
        }
    }

    @TypeConverter
    @JvmStatic
    fun stringToList(data: String?): List<Forecast>? {
        if (data == null) {
            return emptyList()
        }

        return parseResponse<List<Forecast>>(data)
    }

    @TypeConverter
    @JvmStatic
    fun listToString(objects: List<Forecast>): String {
        return jsonConfiguration.encodeToString(objects)
    }

    @TypeConverter
    @JvmStatic
    fun weatherStringToList(data: String?): List<WeatherItem>? {
        if (data == null) {
            return emptyList()
        }
        return parseResponse<List<WeatherItem>>(data)
    }

    @TypeConverter
    @JvmStatic
    fun weatherListToString(objects: List<WeatherItem>): String {
        return jsonConfiguration.encodeToString(objects)
    }

    @TypeConverter
    @JvmStatic
    fun calendarFromTimestamp(value: String?): Date? {
        if (value == null) {
            return null
        }
        return Date(value.toLong())
    }

    @TypeConverter
    @JvmStatic
    fun dateToTimestamp(date: Date): String {
        return "" + date.time
    }
}
