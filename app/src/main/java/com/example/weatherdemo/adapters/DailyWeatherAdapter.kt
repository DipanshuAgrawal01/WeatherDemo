package com.example.weatherdemo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherdemo.data.DailyWeather
import com.example.weatherdemo.databinding.ItemDailyWeatherBinding

class DailyWeatherAdapter : ListAdapter<DailyWeather, DailyWeatherAdapter.ViewHolder>(
    DailyWeatherDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class ViewHolder private constructor(val binding: ItemDailyWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DailyWeather) = with(binding) {
            dailyweather = item
            executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    ItemDailyWeatherBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class DailyWeatherDiffCallback : DiffUtil.ItemCallback<DailyWeather>() {

    override fun areItemsTheSame(oldItem: DailyWeather, newItem: DailyWeather) =
        oldItem.cityId == newItem.cityId && oldItem.dateTimeUnixSeconds == newItem.dateTimeUnixSeconds

    override fun areContentsTheSame(oldItem: DailyWeather, newItem: DailyWeather) =
        oldItem.iconUrl == newItem.iconUrl
                && oldItem.dateTimeUnixSeconds == newItem.dateTimeUnixSeconds
                && oldItem.description == newItem.description
                && oldItem.temperature == newItem.temperature
}