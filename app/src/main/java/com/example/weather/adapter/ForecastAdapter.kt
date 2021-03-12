package com.example.weather.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.weather.databinding.ForecastItemBinding
import com.example.weather.models.Forecast
import com.example.weather.viewbinding.BindingViewHolder
import com.example.weather.viewbinding.createBindingViewHolder

typealias ForecastViewHolder = BindingViewHolder<ForecastItemBinding>

class ForecastAdapter() :
    ListAdapter<Forecast, ForecastViewHolder>(ForecastDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return createBindingViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        with(getItem(position)) {
            holder.binding.forecast = this
            holder.binding.executePendingBindings()
        }
    }
}

private class ForecastDiffCallback : DiffUtil.ItemCallback<Forecast>() {
    override fun areItemsTheSame(oldItem: Forecast, newItem: Forecast): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Forecast, newItem: Forecast): Boolean {
        return oldItem.dt == newItem.dt
    }
}