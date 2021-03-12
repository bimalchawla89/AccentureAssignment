package com.example.weather.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.weather.adapter.ForecastAdapter
import com.example.weather.base.BaseActivity
import com.example.weather.databinding.ActivityMainBinding
import com.example.weather.models.Forecast
import com.example.weather.utils.CommonFunctions
import com.example.weather.viewbinding.viewBinding
import com.example.weather.viewmodel.ForecastViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val _forecastViewModel: ForecastViewModel by viewModels()
    private val _forecastAdapter by lazy { ForecastAdapter() }
    override val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.rvForecast.adapter = _forecastAdapter

        _forecastViewModel.forecastData.observe(this, {
            it?.let {
                binding.progressBar.visibility = View.GONE
                it.forecastList?.let { list ->
                    _forecastAdapter.submitList(ArrayList(CommonFunctions.getConvertedList(list)))
                }
                _forecastViewModel.checkToFetchData(it.date)
            } ?: run {
                _forecastViewModel.fetchData()
            }
        })
    }
}