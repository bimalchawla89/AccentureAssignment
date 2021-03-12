package com.example.weather.models

import android.graphics.Color
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.format.TextStyle
import java.util.*

@Serializable
data class ForecastData(
    val cod: String,
    val message: Int,
    @SerializedName("cnt") val count: Int,
    val list: List<Forecast>,
    val city: City
)

@Serializable
data class Forecast(
    val dt: Long?,
    val main: Main?,
    val weather: List<WeatherItem>?,
    val clouds: Cloud?,
    val wind: Wind?,
    val visibility: Long?,
    val pop: Double?,
    val rain: Rain?,
    @SerializedName("dt_txt") val dtTxt: String?
) {
    fun getWeatherItem(): WeatherItem? {
        return weather?.first()
    }

    fun getDay(): String? {
        return dt?.let { getDateTime(it)?.getDisplayName(TextStyle.FULL, Locale.getDefault()) }
    }

    private fun getDateTime(s: Long): DayOfWeek? {
        return try {
            val formattedDate = dtTxt?.substringBefore(" ") ?: "0000-00-00"
            val splitDate = formattedDate.split("-")
            LocalDate.of(
                splitDate[0].toInt(),
                splitDate[1].toInt(),
                splitDate[2].toInt()
            )
                .dayOfWeek
        } catch (e: Exception) {
            e.printStackTrace()
            DayOfWeek.MONDAY
        }
    }

    fun getColor(): Int {
        return when (dt?.let { getDateTime(it) }) {
            DayOfWeek.MONDAY -> Color.parseColor("#28E0AE")
            DayOfWeek.TUESDAY -> Color.parseColor("#FF0090")
            DayOfWeek.WEDNESDAY -> Color.parseColor("#FFAE00")
            DayOfWeek.THURSDAY -> Color.parseColor("#0090FF")
            DayOfWeek.FRIDAY -> Color.parseColor("#DC0000")
            DayOfWeek.SATURDAY -> Color.parseColor("#0051FF")
            DayOfWeek.SUNDAY -> Color.parseColor("#3D28E0")
            else -> Color.parseColor("#28E0AE")
        }
    }

    fun getHourOfDay(): String {
        return dtTxt?.substringAfter(" ")?.substringBeforeLast(":") ?: "00:00"
    }
}

@Serializable
data class City(
    val id: Int,
    val name: String,
    val country: String,
    val polulation: Long,
    val sunrise: Long,
    val sunset: Long
)

@Serializable
data class WeatherItem(
    val id: Int?,
    val main: String?,
    val description: String?,
    val icon: String?
) {
    fun getDescriptionText(): String? {
        return description?.capitalize(Locale.ROOT)
    }
}

@Serializable
data class Main(
    val temp: Double,
    @SerializedName("feels_like") val feelsLike: Double?,
    @SerializedName("temp_min") var tempMin: Double?,
    @SerializedName("temp_max") var tempMax: Double?,
    val pressure: Double?,
    @SerializedName("sea_level") val seaLevel: Double?,
    @SerializedName("grnd_level") val grndLevel: Double?,
    val humidity: Int?,
    @SerializedName("temp_kf") val tempKf: Double?
) {
    fun getTempString(): String {
        return temp.toString().substringBefore(".") + "°"
    }

    fun getHumidityString(): String {
        return "Humidity $humidity%"
    }

    fun getTempMinString(): String {
        return "Min ${tempMin.toString().substringBefore(".")}°"
    }

    fun getTempMaxString(): String {
        return "Max ${tempMax.toString().substringBefore(".")}°"
    }
}

@Serializable
data class Cloud(
    val all: Int? = 0
) {
    fun getCloudString() : String {
        return "Clouds ${all}%"
    }
}

@Serializable
data class Wind(
    val speed: Double?,
    val deg: Double?
)

@Serializable
data class Rain(
    @SerializedName("3h") val hour: Double? = 0.0
) {
    fun getRainString(): String {
        return "Rain ${hour}mm"
    }
}