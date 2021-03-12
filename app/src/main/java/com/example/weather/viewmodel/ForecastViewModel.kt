package com.example.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.weather.db.entity.ForecastEntity
import com.example.weather.repo.ForecastRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(private val forecastRepository: ForecastRepository) :
    ViewModel() {

    val forecastData: LiveData<ForecastEntity> = forecastRepository.getForecast().asLiveData()

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            forecastRepository.fetchData()
                .catch { println("error "+it.localizedMessage) }
                .collect { forecastRepository.insertForecast(it) }
        }
    }

    fun checkToFetchData(date: Date) {
        val timeDifference = (Date().time - date.time) / (60 * 60 * 1000)
        if (timeDifference >= 3) {
            fetchData()
        }
    }

}